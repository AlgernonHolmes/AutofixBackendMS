package com.repairlist.ms.repairlistms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class RepairlistMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairlistMsApplication.class, args);
	}

}
