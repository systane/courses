import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import * as AWS from 'aws-sdk'
import { CustomAuthorizerEvent, CustomAuthorizerHandler, CustomAuthorizerResult } from 'aws-lambda';
import {verify} from 'jsonwebtoken';
import { JwtToken } from 'src/auth/JwtToken';
import * as middy from 'middy';
import {secretsManager} from 'middy/middlewares';

const secretId = process.env.AUTH_0_SECRET_ID;
const secretField = process.env.AUTH_0_SECRET_FIELD;

const client = new AWS.SecretsManager();

//Cache secret if a lambda instance is reused
let cachedSecret: string

const auth0Authorizer:CustomAuthorizerHandler = async (event: CustomAuthorizerEvent): Promise<CustomAuthorizerResult> => {
    try{
        const decodedToken = await verifyToken(event.authorizationToken);
        console.log('User was authorized');

        return {
            principalId: decodedToken.sub,
            policyDocument: {
                Version: '2012-10-17',
                Statement: [
                    {
                        Action: 'execute-api:Invoke',
                        Effect: 'Allow',
                        Resource: '*'
                    }
                ]
            }
        }
    } catch (e) {
        console.log('User was not authorized', e.message);

        return {
            principalId: 'user',
            policyDocument: {
                Version: '2012-10-17',
                Statement: [
                    {
                        Action: 'execute-api:Invoke',
                        Effect: 'Deny',
                        Resource: '*'
                    }
                ]
            }
        }
    }
}

const verifyToken = async (authHeader: string): Promise<JwtToken> => {
    if(!authHeader) {
        throw new Error('No authorization header');
    }

    if(!authHeader.toLocaleLowerCase().startsWith('bearer ')) {
        throw new Error('Invalid authorization header');
    } 

    const token = authHeader.split(' ')[1];

    const secretObject: any = await getSecret();
    const secret = secretObject[secretField]

   return verify(token, secret) as JwtToken
}

const getSecret = async() => {
    if(cachedSecret) return cachedSecret;

    const data = await client.getSecretValue({
        SecretId: secretId
    }).promise();

    cachedSecret = data.SecretString;

    return JSON.parse(cachedSecret);
}

export const main = middyfy(auth0Authorizer);