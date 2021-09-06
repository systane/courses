import type { AWS } from '@serverless/typescript';

// import hello from '@functions/hello';
import getGroups from '@functions/getGroup';
import createGroups from '@functions/createGroup';
import getImages from '@functions/getImages';
import getimageById from '@functions/getImageById';
import createImages from '@functions/createImage';

const serverlessConfiguration: AWS = {
  service: 'serverless-udagram-app',
  frameworkVersion: '2',
  custom: {
    webpack: {
      webpackConfig: './webpack.config.js',
      includeModules: true,
    },
    documentation: {
      api: {
        info: {
          version: 'v1.0.0',
          title: 'Udagram API',
          description: 'Serverless application for images sharing'
        }
      }
    }
  },
  plugins: ['serverless-webpack', 'serverless-reqvalidator-plugin', 'serverless-aws-documentation'],
  provider: {
    name: 'aws',
    runtime: 'nodejs14.x',
    apiGateway: {
      minimumCompressionSize: 1024,
      shouldStartNameWithService: true,
    },
    environment: {
      AWS_NODEJS_CONNECTION_REUSE_ENABLED: '1',
      GROUPS_TABLE: 'Groups-${self:provider.stage}',
      IMAGES_TABLE: 'Images-${self:provider.stage}',
      IMAGE_ID_INDEX: 'ImageIdIndex',
    },
    lambdaHashingVersion: '20201221',
    iamRoleStatements: [
      {
        Effect: 'Allow',
        Action: [
          'dynamodb:Scan',
          'dynamodb:PutItem',
          'dynamodb:GetItem'
        ],
        Resource: [
          {"Fn::GetAtt": [ 'GroupsDynamoDBTable', 'Arn' ]}
        ]
      },
      {
        Effect: 'Allow',
        Action: [
          'dynamodb:Query'
        ],
        Resource: 'arn:aws:dynamodb:${self:provider.region}:*:table/${self:provider.environment.IMAGES_TABLE}/index/${self:provider.environment.IMAGE_ID_INDEX}'
      },
      {
        Effect: 'Allow',
        Action: [
          'dynamodb:Query',
          'dynamodb:PutItem'
        ],
        Resource: [
          {"Fn::GetAtt": [ 'ImagesDynamoDBTable', 'Arn' ]}
        ]
      },
      // {
      //   Effect: 'Allow',
      //   Action: [
      //     'dynamodb:Scan',
      //     'dynamodb:PutItem',
      //     'dynamodb:DeleteItem'
      //   ],
      //   Resource: [
      //     {"Fn::GetAtt": [ 'ConnectionsDynamoDBTable', 'Arn' ]}
      //   ]
      // },
    ]
  },
  // import the function via paths
  functions: { getGroups, createGroups, getImages, getimageById, createImages },
  resources: {
    Resources: {
      GroupsDynamoDBTable: {
        Type: 'AWS::DynamoDB::Table',
        Properties: {
          TableName: '${self:provider.environment.GROUPS_TABLE}',
          AttributeDefinitions: [
              { AttributeName: 'id', AttributeType: 'S' }
          ],
          KeySchema: [
              { AttributeName: 'id', KeyType: 'HASH' }
          ],
          BillingMode: 'PAY_PER_REQUEST'
        }
      },
      ImagesDynamoDBTable: {
        Type: 'AWS::DynamoDB::Table',
        Properties: {
          TableName: '${self:provider.environment.IMAGES_TABLE}',
          AttributeDefinitions: [
              { AttributeName: 'groupId', AttributeType: 'S' },
              { AttributeName: 'timestamp', AttributeType: 'S' },
              { AttributeName: 'imageId', AttributeType: 'S' },
          ],
          KeySchema: [
              { AttributeName: 'groupId', KeyType: 'HASH' },
              { AttributeName: 'timestamp', KeyType: 'RANGE' },
          ],
          GlobalSecondaryIndexes: [
            {
              IndexName: '${self:provider.environment.IMAGE_ID_INDEX}',
              KeySchema: [
                  { AttributeName: 'imageId', KeyType: 'HASH' },
              ],
              Projection: {
                  ProjectionType: 'ALL' 
              }
            }
          ],
          BillingMode: 'PAY_PER_REQUEST'
        }
      }
    },
      // ConnectionsDynamoDBTable: {
      //   Type: 'AWS::DynamoDB::Table',
      //   Properties: {
      //       TableName: '${self:provider.environment.CONNECTIONS_TABLE}',
      //       AttributeDefinitions: [
      //           { AttributeName: 'id', AttributeType: 'S' }
      //       ],
      //       KeySchema: [
      //           { AttributeName: 'id', KeyType: 'HASH' }
      //       ],
      //       BillingMode: 'PAY_PER_REQUEST'
      //   }
      // },
  }
};

module.exports = serverlessConfiguration;
