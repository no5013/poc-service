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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping("/hello/1")
    public String helloFromAnotherService(@RequestParam(required = false, defaultValue = "0") Integer delay){
        String url = UriComponentsBuilder.fromUriString("http://poc-service-b/hello")
                .queryParam("delay", delay)
                .build()
                .toString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                String.class
        ).getBody();
    }

    @GetMapping("/hello/2")
    public String helloFromAnotherService2(@RequestParam(required = false, defaultValue = "0") Integer delay){
        String url = UriComponentsBuilder.fromUriString("http://poc-service-b/hello2")
                .queryParam("delay", delay)
                .build()
                .toString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                String.class
        ).getBody();
    }

    @GetMapping("/hello/3")
    public String helloFromAnotherService3(@RequestParam(required = false, defaultValue = "0") Integer delay){
        String url = UriComponentsBuilder.fromUriString("http://poc-service-b/hello3")
                .queryParam("delay", delay)
                .build()
                .toString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                String.class
        ).getBody();
    }

    @GetMapping("/hello/4")
    public String helloFromAnotherService4(@RequestParam(required = false, defaultValue = "0") Integer delay){
        String url = UriComponentsBuilder.fromUriString("http://poc-service-b/hello4")
                .queryParam("delay", delay)
                .build()
                .toString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                String.class
        ).getBody();
    }

    @GetMapping("/hello/5")
    public String helloFromAnotherService5(@RequestParam(required = false, defaultValue = "0") Integer delay){
        String url = UriComponentsBuilder.fromUriString("http://poc-service-b/hello5")
                .queryParam("delay", delay)
                .build()
                .toString();
        return restTemplate.exchange(
                url,
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
