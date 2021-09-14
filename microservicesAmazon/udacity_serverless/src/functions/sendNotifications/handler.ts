import { S3Event, SNSEvent, SNSHandler } from "aws-lambda";
import { middyfy } from '@libs/lambda';
import 'source-map-support/register'
import * as AWS from 'aws-sdk';


const docClient = new AWS.DynamoDB.DocumentClient();
const connectionsTable = process.env.CONNECTIONS_TABLE;
const stage = process.env.STAGE;
const apiId = process.env.API_ID;

const connectionParams = {
    apiVersion: "2018-11-29",
    endpoint: `${apiId}.execute-api.us-east-1.amazonaws.com/${stage}`
};

const apiGateway = new AWS.ApiGatewayManagementApi(connectionParams);
const sendNotifications: SNSHandler = async (event: SNSEvent) => {
    console.log('Processing SNS event ', JSON.stringify(event));

    for (const snsRecord of event.Records) {
        const s3Event = snsRecord.Sns.Message;
        console.log('Processing S3 event', s3Event);

        const s3EventObject = JSON.parse(s3Event);

        await processS3Event(s3EventObject);
    }
}
const processS3Event = async (event:S3Event) => {
    for (const record of event.Records) {
        const key = record.s3.object.key;
        console.log('Processing S3 item with key: ', key);

        const connections = await docClient.scan({
            TableName: connectionsTable
        }).promise();

        const payload = {
            imageId: key
        };

        for (const connection of connections.Items) {
            const connectionId = connection.id;
            await sendMessageToClient(connectionId, payload);
        }
    }
}

const sendMessageToClient = async (connectionId, payload) => {
    
    try {
        console.log('Sending message to a connection', connectionId);

        await apiGateway.postToConnection({
            ConnectionId: connectionId,
            Data: JSON.stringify(payload),
        }).promise();

    } catch(e) {
        console.log('Failed to send message', JSON.stringify(e));
        if(e.statusCode === 410){
            console.log('Stale connection')

            await docClient.delete({
                TableName: connectionsTable,
                Key: {
                    id: connectionId
                }
            }).promise();
        }
    }
}

export const main = middyfy(sendNotifications);