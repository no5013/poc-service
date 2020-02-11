package com.poc.dummyservicea;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private RestTemplate restTemplate;

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

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
