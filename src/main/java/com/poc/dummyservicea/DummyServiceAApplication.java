package com.poc.dummyservicea;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DummyServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(DummyServiceAApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "What the f*ck";
    }
}
