import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import * as AWS from 'aws-sdk'
import { APIGatewayProxyEventV2, APIGatewayProxyHandlerV2, APIGatewayProxyResultV2 } from 'aws-lambda';
import * as uuid from 'uuid';

const docClient = new AWS.DynamoDB.DocumentClient()
const groupsTable = process.env.GROUPS_TABLE

const createGroups: APIGatewayProxyHandlerV2 = async (event: APIGatewayProxyEventV2): Promise<APIGatewayProxyResultV2> => {
  console.log('Processing event: ', event)
  const id = uuid.v4()

  const stringfiedBody = JSON.stringify(event.body)
  const parsedBody = JSON.parse(stringfiedBody);

  const newItem = {
    id,
    ...parsedBody
  }
 
  await docClient.put({
    TableName: groupsTable,
    Item: newItem
  }).promise()


  return {
    statusCode: 201,
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    body: JSON.stringify({newItem})
  }
}

export const main = middyfy(createGroups);
