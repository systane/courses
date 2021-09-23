import { JwtToken } from "src/auth/JwtToken";
import { decode } from 'jsonwebtoken';


export function getUserId(jwtToken: string): string {
    const decodedJwt = decode(jwtToken) as JwtToken;
    return decodedJwt.sub;
}