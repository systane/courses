package com.example.Oauth.Server.config;

import com.example.Oauth.Server.domain.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableAuthorizationServer //Habilita o AuthorizationServer disponibilizando um AuthorizationEndpoint e um TokenEndpoint
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static PasswordEncoder encoder;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.authorized-grant-types}")
    private String[] authorizedGrantTypes;

    @Value("${security.oauth2.client.resource-ids}")
    private String resourceIds;

    @Value("${security.oauth2.client.scope}")
    private String [] scopes;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    @Value("${security.oauth2.client.access-token-validity-seconds}")
    private Integer accessTokenValiditySeconds;

    @Autowired
    DataSource dataSource;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JdbcTokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    /**
     * Esse método configura um gerenciador de autencicação do AuthorizationEndpoint.
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager).tokenStore(tokenStore());
    }


    /**
     * Esse método registra um client bem como as suas configurações.
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.jdbc(dataSource)
                .withClient(clientId)
                .authorizedGrantTypes(authorizedGrantTypes)
                .authorities(Authorities.names())
                .resourceIds(resourceIds)
                .scopes(scopes)
                .secret(secret)
                .accessTokenValiditySeconds(accessTokenValiditySeconds);
    }

    /**
     * Esse método configura o tipo de criptografia utilizada no password
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        if(Objects.isNull(encoder)){
            encoder = new BCryptPasswordEncoder();
        }

        return encoder;
    }
}
