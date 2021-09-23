import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import { APIGatewayProxyEventV2, APIGatewayProxyHandlerV2, APIGatewayProxyResultV2 } from 'aws-lambda';
import { CreateGroupRequest } from 'src/requests/CreateGroupRequest';
import { createGroup } from 'src/businessLogic/groups';

const createGroups: APIGatewayProxyHandlerV2 = async (event: APIGatewayProxyEventV2): Promise<APIGatewayProxyResultV2> => {
  console.log('Processing event: ', event)

  const stringfiedBody = JSON.stringify(event.body)
  const newGroup: CreateGroupRequest = JSON.parse(stringfiedBody);
  const authorization = event.headers.Authorization;
  const jwtToken = authorization.split(' ')[1];

  const newItem = await createGroup(newGroup, jwtToken);

  return {
    statusCode: 201,
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    body: JSON.stringify({newItem})
  }
}

export const main = middyfy(createGroups);
