package com.rui.eureka_moudle_three;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMoudleThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMoudleThreeApplication.class, args);
    }

}
