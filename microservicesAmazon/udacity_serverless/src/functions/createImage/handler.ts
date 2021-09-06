import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import { notFoundResponse } from '@libs/responses';
import { APIGatewayProxyEventV2, APIGatewayProxyHandlerV2, APIGatewayProxyResultV2 } from 'aws-lambda';
import * as AWS from 'aws-sdk'
import * as uuid from 'uuid';

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

const createImage = async(groupId: string, imageId: string, body: any) => {
  const timestamp = new Date().toISOString();
  const newImage = {
    groupId,
    timestamp,
    imageId,
    ...body
  };

  console.log('Storing new item: ', newImage);

  await docClient.put({
    TableName: imagesTable,
    Item: newImage
  }).promise();
}

const createImages: APIGatewayProxyHandlerV2 = async (event: APIGatewayProxyEventV2): Promise<APIGatewayProxyResultV2> => {
  console.log('Processing event: ', event)
  const groupId = event.pathParameters.groupId;
  const imageId = uuid.v4()

  const isGroupIdValid = await validateGroupId(groupId);
  if(!isGroupIdValid){
    return notFoundResponse('Group does not exist')
  }

  const stringfiedBody = JSON.stringify(event.body)
  const parsedBody = JSON.parse(stringfiedBody);
  const newItem = await createImage(groupId, imageId, parsedBody);

  return {
    statusCode: 201,
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    body: JSON.stringify({newItem})
  }
  
}

export const main = middyfy(createImages);
