package com.repair.ms.repairms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class RepairMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepairMsApplication.class, args);
    }

}
