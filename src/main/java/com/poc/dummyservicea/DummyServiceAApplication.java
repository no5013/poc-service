package com.poc.dummyservicea;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class DummyServiceAApplication {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DummyServiceAApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "What the f*ck";
    }

    @GetMapping("/hello/{service}")
    public String helloFromAnotherService(@PathVariable String service){
        return restTemplate.exchange(
                String.join("","http://",service,"/hello"),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                String.class
        ).getBody();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
