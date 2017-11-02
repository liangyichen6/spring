package com.ivan.springcloud.usrmgt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * @author liangyi
 * @Date 2017年11月2日
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserManagementApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(UserManagementApplication.class).run(args);
	}
}
