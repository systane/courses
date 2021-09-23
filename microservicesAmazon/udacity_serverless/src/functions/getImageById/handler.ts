import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import { notFoundResponse } from '@libs/responses';
import { APIGatewayProxyEventV2, APIGatewayProxyHandlerV2, APIGatewayProxyResultV2 } from 'aws-lambda';
import * as AWS from 'aws-sdk'
import * as AWSXRAY from 'aws-xray-sdk';

const XAWS = AWSXRAY.captureAWS(AWS);

const docClient = new XAWS.DynamoDB.DocumentClient();
const imagesTable = process.env.IMAGES_TABLE;
const imageIdIndex = process.env.IMAGE_ID_INDEX;

const getImageById: APIGatewayProxyHandlerV2 = async (event: APIGatewayProxyEventV2): Promise<APIGatewayProxyResultV2> => {
  console.log('Processing event: ', event)
  const imageId = event.pathParameters.imageId;

  const result = await docClient.query({
    TableName: imagesTable,
    IndexName: imageIdIndex,
    KeyConditionExpression: 'imageId = :imageId',
    ExpressionAttributeValues: {
      ':imageId': imageId
    }
  }).promise();

  if(result.Count !== 0){
    return {
      statusCode: 200,
      headers: {
        'Access-Control-Allow-Origin': '*'
      },
      body: JSON.stringify(result.Items[0])
    }
  }

  return notFoundResponse('Group does not exist')
}

export const main = middyfy(getImageById);
