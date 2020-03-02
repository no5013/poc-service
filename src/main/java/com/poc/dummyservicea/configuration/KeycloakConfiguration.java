package com.poc.dummyservicea.configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    @Bean
    public Keycloak getKeycloakClient(){
        String serverUrl = "http://localhost:8180/auth";
        String realm = "public-library";
        String clientId = "admin-cli";

        Keycloak keycloak = KeycloakBuilder.builder() //
                .serverUrl(serverUrl) //
                .realm(realm) //
                .grantType(OAuth2Constants.PASSWORD) //
                .clientId(clientId) //
//                .clientSecret(clientSecret) //
                .username("admin") //
                .password("password") //
                .build();

        return keycloak;
    }
}
