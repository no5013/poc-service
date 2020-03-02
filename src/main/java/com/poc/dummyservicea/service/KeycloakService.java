package com.poc.dummyservicea.service;

import com.poc.dummyservicea.model.AppSource;
import com.poc.dummyservicea.model.ISprintUser;
import com.poc.dummyservicea.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserSessionRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KeycloakService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Keycloak keycloakClient;

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password, AppSource source){
        ISprintUser iSprintUser = userRepository.getUserByUsername(username);
        this.logout(iSprintUser.getId(), source);
        return getAccessToken("password", source, username, password);
    }

    public String getAccessToken(String grantType, AppSource source, String username, String password){
        String keycloakHost = "http://localhost:8180";
        String loginPath = "/auth/realms/public-library/protocol/openid-connect/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grantType);
        body.add("username", username);
        body.add("password", password);

        if(source.equals(AppSource.WEB)){
            body.add("client_id", "spring-boot-app");
            body.add("client_secret", "61b4734d-ea18-4ace-8db1-31ad036c392a");
        }else{
            body.add("client_id", "spring-boot-app-mobile");
            body.add("client_secret", "070b7d21-1bbc-4117-85b2-d949882deec6");
        }

        return restTemplate.exchange(
                keycloakHost + loginPath,
                HttpMethod.POST,
                new HttpEntity<>(body, httpHeaders),
                String.class
        ).getBody();
    }

    public Object logout(String userId, AppSource source){
        String realm = "public-library";
        RealmResource realmResource = keycloakClient.realm(realm);
        UsersResource userResource = realmResource.users();
        List<UserSessionRepresentation> sessions = userResource.get(userId).getUserSessions();

        String client = source.equals(AppSource.WEB) ? "spring-boot-app" : "spring-boot-app-mobile";

        for(UserSessionRepresentation session : sessions){
            log.info("{}", session.getClients());
            for(Map.Entry<String, String> clientSet : session.getClients().entrySet()){
                if(clientSet.getValue().equalsIgnoreCase(client))
                    realmResource.deleteSession(session.getId());
            }
        }

        return userResource.get(userId).getUserSessions();
    }
}
