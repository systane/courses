import { handlerPath } from '@libs/handlerResolver';

export default {
  handler: `${handlerPath(__dirname)}/handler.main`,
  events: [
    {
      stream: {
        type: 'dynamodb',
        arn: { 'Fn::GetAtt': ['ImagesDynamoDBTable', 'StreamArn'] },
      }
    }
  ],
  environment: {
    ES_ENDPOINT: { 'Fn::GetAtt': ['ImagesSearch', 'DomainEndpoint'] }
  },
}
