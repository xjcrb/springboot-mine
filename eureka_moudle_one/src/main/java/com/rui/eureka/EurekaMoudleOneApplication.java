package com.rui.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMoudleOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMoudleOneApplication.class, args);
    }

}
