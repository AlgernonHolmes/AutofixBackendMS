package com.vehicle.ms.vehiclems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class VehicleMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleMsApplication.class, args);
    }

}
