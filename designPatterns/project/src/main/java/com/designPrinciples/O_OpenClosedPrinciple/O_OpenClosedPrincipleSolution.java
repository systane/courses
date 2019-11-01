package com.designPrinciples.O_OpenClosedPrinciple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class O_OpenClosedPrincipleSolution {

    @Getter
    @Setter
    private static class ClientDTO{
        private String Email;
        private String password;
    }

    @AllArgsConstructor
    private static class AuthenticateLogin {
        private Authentication authentication;

        public boolean login(ClientDTO client){
            return authentication.login(client);
        }
    }

    private interface Authentication {
        boolean login(ClientDTO client);
    }

    private static class AuthenticationGoogle implements Authentication {
        @Override
        public boolean login(ClientDTO client) {
            //Authenticate the login using Google's Api
            //If the information is correct return true, else false.
        }
    }

    private static class AuthenticationFacebook implements Authentication {
        @Override
        public boolean login(ClientDTO client) {
            //Authenticate the login using Facebook's Api
            //If the information is correct return true, else false.
        }
    }

    private static class AuthenticationDatabase implements Authentication {
        @Override
        public boolean login(ClientDTO client) {
            //authenticate the login with database information
            //If the information is correct return true, else false
        }
    }
}
