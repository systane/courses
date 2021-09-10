import { middyfy } from '@libs/lambda';
import { DynamoDBStreamEvent, DynamoDBStreamHandler } from 'aws-lambda';
import 'source-map-support/register';
import * as elasticseach from 'elasticsearch';
import * as httpAwsEs from 'http-aws-es';

const eHost = process.env.ES_ENDPOINT;

const es = new elasticseach.Client({
    hosts: [eHost],
    connectionClass: httpAwsEs
});


const elasticSearchSync: DynamoDBStreamHandler = async (event:DynamoDBStreamEvent) => {
    console.log('Processing events batch from DynamoDB', JSON.stringify(event));

    for (const record of event.Records) {
        console.log('Processing record', JSON.stringify(record));

        if(record.eventName !== 'INSERT'){
            continue;
        }

        const newItem = record.dynamodb.NewImage;
        const imageId = newItem.imageId.S;

        const body = {
            imageId: newItem.imageId.S,
            groupId: newItem.groupId.S,
            imageUrl: newItem.imageUrl.S,
            title: newItem.title.S, 
            timestamp: newItem.timestamp.S,
        }

        await es.index({
            index: 'images-index',
            type: 'images',
            id: imageId,
            body
        });
    }
    
};

export const main = middyfy(elasticSearchSync);