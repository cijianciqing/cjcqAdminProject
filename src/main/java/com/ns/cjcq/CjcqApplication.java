package com.ns.cjcq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CjcqApplication {

    public static void main(String[] args) {
        SpringApplication.run(CjcqApplication.class, args);
    }

    @GetMapping("/hello")
    public String initTest(){
        return "this is com.ns.cjcq.CjcqApplicationTests!!!Nice Try";
    }

}
