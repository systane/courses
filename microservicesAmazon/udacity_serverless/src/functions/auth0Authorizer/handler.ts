import 'source-map-support/register';

import { middyfy } from '@libs/lambda';
import { CustomAuthorizerEvent, CustomAuthorizerResult } from 'aws-lambda';
import {verify} from 'jsonwebtoken';
import { JwtToken } from 'src/auth/JwtToken';
// import { secretsManager } from 'middy/middlewares';

// const secretId = process.env.AUTH_0_SECRET_ID;
// const secretField = process.env.AUTH_0_SECRET_FIELD;

const cert = `-----BEGIN CERTIFICATE-----
MIIDDTCCAfWgAwIBAgIJPe4cAGTrVifqMA0GCSqGSIb3DQEBCwUAMCQxIjAgBgNV
BAMTGWRldi1zN2p0ZmY5Ny51cy5hdXRoMC5jb20wHhcNMjEwOTE1MDAwMzM3WhcN
MzUwNTI1MDAwMzM3WjAkMSIwIAYDVQQDExlkZXYtczdqdGZmOTcudXMuYXV0aDAu
Y29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1/2AxA10wl452Pl9
x6RFTH1UzesoCHCgjF4KQJzJHwBp+I3qgzUHc8OZvFbFGOwJBe9GTFac8s0l11MQ
uFbIa7t07Qff/5OrHi2VD/CaSl/oPn4k++3RB03X7v3qL+MuM7Wk5LbyFCnQFfMn
R3lXdj7ZhBnWCHzhw0HO+MGlj3VIuVUrOlNGS9m1jvDlCcIs63pTMOS7iDaWQoNX
1O4S97wxiU9XjS33Qf/CJ4CVuccPS9tmtQ9F9wwFaYA2LejlxTrkYai4qmD5ka5V
+naZO6/PDeHAbj/ez05TdDZtiDVLreWBNzH4BzcDDrA0T5vvycDP0QIFgu2x5Rwt
mJs32QIDAQABo0IwQDAPBgNVHRMBAf8EBTADAQH/MB0GA1UdDgQWBBTGUxnCnw6P
CXNWxicX9jyvjhUlfjAOBgNVHQ8BAf8EBAMCAoQwDQYJKoZIhvcNAQELBQADggEB
AEjMeiC59XUIXsIDsrvpJ6fHLBxFp5k0R1Tfth0HnIVc1cn8MXu4MCgJKifnpuln
UrTbMHfKzNubqRcb3yT7Dhaj9o1A7ucmlFMAnvBkptU/9KO8I+gI4WgaXcJEdAuA
ACBFFklGqvulPLagqqiR1RUvn0th/CE6KOSJ0Pgw26cV9hkO0YZKM8+hfSn0ePYM
nu1hvX6OM3fG3qIuJh9lJ94EXUjaUyTD8t0WNSWJd1tgXfC3WIMrit6YKyDVDD4k
ovdc8trRTWyICJo0TVUcBAqi/B6/ThUz2uaTtGo+cAiBIInDxWq2dsqzoBvnfaVs
kPIx1fLpdFhfrg3cb1HkIuY=
-----END CERTIFICATE-----`;

const auth0Authorizer = async (event: CustomAuthorizerEvent): Promise<CustomAuthorizerResult> => {
    try{
        const decodedToken = verifyToken(event.authorizationToken);
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

const verifyToken = (authHeader: string): JwtToken => {
    if(!authHeader) {
        throw new Error('No authorization header');
    }

    if(!authHeader.toLocaleLowerCase().startsWith('bearer ')) {
        throw new Error('Invalid authorization header');
    } 

    const token = authHeader.split(' ')[1];

   return verify(token, cert, {algorithms: ['RS256']}) as JwtToken
}

export const main = middyfy(auth0Authorizer);

// export const main = middyfy(auth0Authorizer).use(secretsManager({
//     cache: true,
//     cacheExpiryInMillis: 60000,
//     throwOnFailedCall: true,
//     secrets: {
//         AUTH0_SECRET: secretId
//     }
// }));