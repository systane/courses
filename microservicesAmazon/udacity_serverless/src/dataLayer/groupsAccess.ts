import * as AWS from 'aws-sdk';
import * as AWSXRAY from 'aws-xray-sdk';
import { DocumentClient } from 'aws-sdk/clients/dynamodb';
import { Group } from '../models/Group';

const XAWS = AWSXRAY.captureAWS(AWS);

export class GroupAccess {

    constructor(
        private readonly docClient: DocumentClient = createDynamoDBClient(),
        private readonly groupsTable = process.env.GROUPS_TABLE
    ){};

    async getAllGroups(): Promise<Group[]> {
        console.log('Getting all groups');

        const result = await this.docClient.scan({
            TableName: this.groupsTable
        }).promise();

        const items = result.Items;
        return items as Group[];
    };

    async createGroup(group: Group): Promise<Group> {
        console.log(`Creating a group with id ${group.id}`);

        await this.docClient.put({
            TableName: this.groupsTable,
            Item: group
        }).promise();

        
        return group;
    };
}

function createDynamoDBClient() {
    if(process.env.IS_OFFLINE) {
        console.log('Creating a local dynamodb instance');
        return new XAWS.DynamoDB.DocumentClient({
            region: 'localhost',
            endpoint: 'http://localhost:8000'
        });
    }
    return new XAWS.DynamoDB.DocumentClient();
}