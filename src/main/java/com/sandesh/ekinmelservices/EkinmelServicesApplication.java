package com.sandesh.ekinmelservices;

import com.sandesh.ekinmelservices.model.Status;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class EkinmelServicesApplication {

    @Bean
    public Status getStatus() {
        Status status = new Status();
        status.setMessage("Exception occured in Ekinmel Service");
        status.setOperation("Unknown Operation");
        return status;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EkinmelServicesApplication.class, args);
    }

}
