import type { AWS } from '@serverless/typescript';

// import hello from '@functions/hello';
import getGroups from '@functions/getGroup';
import createGroups from '@functions/createGroup';

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
          'dynamodb:Scan',
          'dynamodb:PutItem',
          'dynamodb:DeleteItem'
        ],
        Resource: [
          {"Fn::GetAtt": [ 'ConnectionsDynamoDBTable', 'Arn' ]}
        ]
      },
    ]
  },
  // import the function via paths
  functions: { getGroups, createGroups },
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
      ConnectionsDynamoDBTable: {
        Type: 'AWS::DynamoDB::Table',
        Properties: {
            TableName: '${self:provider.environment.CONNECTIONS_TABLE}',
            AttributeDefinitions: [
                { AttributeName: 'id', AttributeType: 'S' }
            ],
            KeySchema: [
                { AttributeName: 'id', KeyType: 'HASH' }
            ],
            BillingMode: 'PAY_PER_REQUEST'
        }
      },
    }
  }
};

module.exports = serverlessConfiguration;
