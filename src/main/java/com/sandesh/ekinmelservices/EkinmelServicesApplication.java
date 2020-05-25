package com.sandesh.ekinmelservices;

import com.sandesh.ekinmelservices.models.Status;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EkinmelServicesApplication {

    @Bean
    public Status getStatus() {
        Status status = new Status();
        status.setMessage("Exception occured in Ekinmel Service");
        status.setOperation("Unknown Operation");
        return new Status();
    }

    @Bean
    public RestTemplate getRestTemplate() { return new RestTemplate(); }

    public static void main(String[] args) {
        SpringApplication.run(EkinmelServicesApplication.class, args);
    }

}
