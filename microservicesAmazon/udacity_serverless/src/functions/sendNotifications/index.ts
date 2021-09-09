import { handlerPath } from '@libs/handlerResolver';

export default {
  handler: `${handlerPath(__dirname)}/handler.main`,
  events: [
    {
      s3: {
        bucket: 'serverless-udagram-image-dev',
        event: 's3:ObjectCreated:*',
        existing: true
      }
    }
  ]
}
