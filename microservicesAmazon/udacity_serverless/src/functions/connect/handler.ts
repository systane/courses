import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import * as AWS from 'aws-sdk'
import { APIGatewayProxyEvent, APIGatewayProxyHandler, APIGatewayProxyResult } from 'aws-lambda';

const docClient = new AWS.DynamoDB.DocumentClient();
const connectionsTable = process.env.CONNECTIONS_TABLE;


const connect:APIGatewayProxyHandler = async (event: APIGatewayProxyEvent): Promise<APIGatewayProxyResult> => {
    console.log('Websocket connect', event);

    const connectionId = event.requestContext.connectionId
    const timestamp = new Date().toISOString();

    const item = {
        id: connectionId,
        timestamp
    };

    await docClient.put({
        TableName: connectionsTable,
        Item: item
    }).promise();

    return {
        statusCode: 200,
        body: ''
    }
}

export const main = middyfy(connect);