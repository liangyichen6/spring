package com.ivan.springcloud.usrmgt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.springcloud.usrmgt.bean.User;
import com.ivan.springcloud.usrmgt.feignclient.ImageSeverClient;

@RestController
public class MainController {

	@Autowired
	private ImageSeverClient imageServerClient;

	@GetMapping(value = "user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		User u = new User();
		u.setUserId(1);
		u.setFirstName("Ivan");
		u.setLastName("Chan");
		u.setUsername("Ivan Chan");
		u.setImageUrls(imageServerClient.getUserImages(userId));
		return ResponseEntity.ok().body(u);
	}
}
