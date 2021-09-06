import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import { notFoundResponse } from '@libs/responses';
import { APIGatewayProxyEventV2, APIGatewayProxyHandlerV2, APIGatewayProxyResultV2 } from 'aws-lambda';
import * as AWS from 'aws-sdk'

const docClient = new AWS.DynamoDB.DocumentClient();
const imagesTable = process.env.IMAGES_TABLE;
const groupsTable = process.env.GROUPS_TABLE;

const validateGroupId = async (groupId:string) => {
  const result = await docClient.get({
    TableName: groupsTable,
    Key: {
      id: groupId
    }
  }).promise();

  console.log('Get group: ', result);
  return !!result.Item
}

const getImagesPerGroup = async (groupId: string) => {
  const result = await docClient.query({
    TableName: imagesTable,
    KeyConditionExpression: 'groupId = :groupId',
    ExpressionAttributeValues: {
      ':groupId': groupId
    },
    ScanIndexForward: false
  }).promise();

  return result.Items;
}

const getImages: APIGatewayProxyHandlerV2 = async (event: APIGatewayProxyEventV2): Promise<APIGatewayProxyResultV2> => {
  console.log('Processing event: ', event)
  const groupId = event.pathParameters.groupId;

  const isGroupIdValid = await validateGroupId(groupId);
  if(!isGroupIdValid){
    return notFoundResponse('Group does not exist')
  }

  const images = await getImagesPerGroup(groupId);

  return {
    statusCode: 200,
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    body: JSON.stringify({items: images})
  }
  
}

export const main = middyfy(getImages);
