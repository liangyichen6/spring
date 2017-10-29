package com.ivan.springcloud.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.springcloud.eurekaserver.configuration.EurekaConfiguration;

@EnableEurekaServer
@SpringBootApplication
@RestController
public class EurekaServerApplication {

	@Autowired
	private EurekaConfiguration eurekaConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

	@GetMapping("/foo/description")
	public String getPropertyFromConfig() {
		return eurekaConfiguration.getDescription();
	}
}
