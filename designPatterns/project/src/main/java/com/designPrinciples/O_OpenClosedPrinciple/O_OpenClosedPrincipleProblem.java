package com.designPrinciples.O_OpenClosedPrinciple;

import lombok.Getter;
import lombok.Setter;

public class O_OpenClosedPrincipleProblem {

    @Getter
    @Setter
    private static class ClientDTO{
        private String Email;
        private String password;
    }

    private static class AuthenticateLogin{
        public boolean login(ClientDTO client, String provider){
            if(provider.equalsIgnoreCase("Google")){
                //authenticate the login with OAuth
                //If the information is correct return true, else false
            }
            else{
                //authenticate the login with database information
                //If the information is correct return true, else false
            }
        }
    }
}
