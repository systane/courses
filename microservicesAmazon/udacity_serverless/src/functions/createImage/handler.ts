import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import { notFoundResponse } from '@libs/responses';
import { APIGatewayProxyEventV2, APIGatewayProxyHandlerV2, APIGatewayProxyResultV2 } from 'aws-lambda';
import * as AWS from 'aws-sdk'
import * as uuid from 'uuid';
import { cors } from 'middy/middlewares';

const docClient = new AWS.DynamoDB.DocumentClient();
const s3 = new AWS.S3({signatureVersion: 'v4'});

const imagesTable = process.env.IMAGES_TABLE;
const groupsTable = process.env.GROUPS_TABLE;
const bucketName = process.env.IMAGES_S3_BUCKET;
const urlExpiration = parseInt(process.env.SIGNED_URL_EXPIRATION);


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
    ...body,
    imageUrl: `https://${bucketName}.s3.amazonaws.com/${imageId}`
  };

  console.log('Storing new item: ', newImage);

  await docClient.put({
    TableName: imagesTable,
    Item: newImage
  }).promise();

  return newImage;
}

const getUploadUrl = (imageId: string) => {
  return s3.getSignedUrl('putObject', {
    Bucket: bucketName,
    Key: imageId,
    Expires: urlExpiration
  });
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
  const url = getUploadUrl(imageId);

  return {
    statusCode: 201,
    body: JSON.stringify({
      newItem: newItem,
      uploadUrl: url
    })
  }
  
}

export const main = middyfy(createImages).use(cors({
  credentials: true
}));
