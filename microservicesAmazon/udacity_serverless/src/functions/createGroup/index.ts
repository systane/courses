import schema from './schema';
import { handlerPath } from '@libs/handlerResolver';

export default {
  handler: `${handlerPath(__dirname)}/handler.main`,
  events: [
    {
      http: {
        method: 'post',
        path: 'groups',
        cors: true,
        authorizer: {name: 'auth0Authorizer'},
        request: {
          schema: {
            'application/json': schema
          }
        }
      }
    }
  ]
}
