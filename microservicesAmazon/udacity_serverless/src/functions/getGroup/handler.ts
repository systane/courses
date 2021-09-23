import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import { getAllGroups } from 'src/businessLogic/groups';

const getGroups = async (event) => {
  console.log('Processing event: ', event)

  const groups = await getAllGroups();

  return {
    statusCode: 200,
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    body: JSON.stringify({
      items: groups
    })
  }
}

export const main = middyfy(getGroups);
