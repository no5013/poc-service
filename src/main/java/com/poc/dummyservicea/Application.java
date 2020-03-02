package com.poc.dummyservicea;

import com.poc.dummyservicea.model.AppSource;
import com.poc.dummyservicea.service.KeycloakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import th.co.scb.accesshelper.annotation.RequireLogin;

import java.util.Map;

@Slf4j
@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"th.co.scb", "com.poc"})
public class Application {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LiveService liveService;

    @Autowired
    private KeycloakService keycloakService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/stub")
    public ResponseEntity getReceive(){
        return ResponseEntity.ok("GET OK");
    }

    @PostMapping("/stub")
    public ResponseEntity postReceive(){
        return ResponseEntity.ok("POST OK");
    }

    @PutMapping("/stub")
    public ResponseEntity putReceive(){
        return ResponseEntity.ok("PUT OK");
    }

    @DeleteMapping("/stub")
    public ResponseEntity deleteReceive(){
        return ResponseEntity.ok("DELETE OK");
    }

    @PatchMapping("/stub")
    public ResponseEntity patchReceive(){
        return ResponseEntity.ok("PATCH OK");
    }

    @RequireLogin
    @GetMapping(value = "/headers", produces = "application/json")
    public ResponseEntity getHeaders(@RequestHeader HttpHeaders httpHeaders){
        return ResponseEntity.ok(httpHeaders);
    }

    @PostMapping("/restapi")
    public ResponseEntity<?> testCall(@RequestBody Map<String, Object> requestBody){
        log.info("BODY: {}", requestBody);
        String url = (String) requestBody.get("path");
        String method = (String) requestBody.get("method");
        Object body = requestBody.get("body");
        return restTemplate.exchange(
                url,
                HttpMethod.valueOf(method),
                new HttpEntity<>(body, new HttpHeaders()),
                String.class
        );
    }

    @GetMapping("/live")
    public ResponseEntity<?> curLive(){
        return ResponseEntity.ok("CURRENT LIVE: " + liveService.getCurrentLive());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader String source, @RequestBody Map<String, Object> requestBody){
        String username = (String) requestBody.get("username");
        String password = (String) requestBody.get("password");
        AppSource appSource = AppSource.valueOf(source.toUpperCase());
        return ResponseEntity.ok(keycloakService.login(username, password, appSource));
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
