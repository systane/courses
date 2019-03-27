package com.example.orderservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Classe responsável por mapear cada role para seu method específico
 */
@Configuration
@EnableResourceServer
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    private final static String resourceId = "resources";


    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .antMatchers(HttpMethod.GET, "/**").access("#oauth.hasScope('read')")
                .antMatchers(HttpMethod.OPTIONS, "/**").access("#oauth.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth.hasScope('write')");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId(resourceId);
    }
}
