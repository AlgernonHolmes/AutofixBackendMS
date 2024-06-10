package com.reports.ms.reportsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ReportsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsMsApplication.class, args);
	}

}
