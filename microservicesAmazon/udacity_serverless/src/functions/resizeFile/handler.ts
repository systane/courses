import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import * as AWS from 'aws-sdk'; 
import { S3EventRecord, SNSEvent, SNSHandler } from 'aws-lambda';
import * as Jimp from 'jimp';

const s3 = new AWS.S3();

const imagesBucketName = process.env.IMAGES_S3_BUCKET;
const thumbnailBucketName = process.env.THUMBNAILS_S3_BUCKET;

const resizeImage: SNSHandler = async (event: SNSEvent) => {
  console.log('Processing SNS event ', JSON.stringify(event));

  for (const snsRecord of event.Records) {
      const s3Event = snsRecord.Sns.Message;
      console.log('Processing S3 event', s3Event);

      const s3EventObject = JSON.parse(s3Event);

      for (const record of s3EventObject.Records) {
        await processImage(record);
      }
  }
}

const processImage = async (record: S3EventRecord) => {
  const key = record.s3.object.key;
  console.log('Processing S3 item with key: ', key);

  const response = await s3.getObject({
    Bucket: imagesBucketName,
    Key: key
  }).promise();

  const body = response.Body!
  const image = await Jimp.read(body as any)

  console.log('Resizing image');
  image.resize(150, Jimp.AUTO);
  const convertedBuffer = await image.getBufferAsync(Jimp.MIME_JPEG);

  console.log(`Writing image back to S3 bucket: ${thumbnailBucketName}`);
  await s3.putObject({
    Bucket: thumbnailBucketName,
    Key: `${key}.jpeg`,
    Body: convertedBuffer
  }).promise();
}

export const main = middyfy(resizeImage);
