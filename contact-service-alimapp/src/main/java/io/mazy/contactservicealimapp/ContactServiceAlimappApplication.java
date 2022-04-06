package io.mazy.contactservicealimapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ContactServiceAlimappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactServiceAlimappApplication.class, args);
    }

}
