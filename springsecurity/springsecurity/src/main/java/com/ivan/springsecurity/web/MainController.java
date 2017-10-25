package com.ivan.springsecurity.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@PostMapping("test_csrf")
	public String testCsrfFunction() {
		return "csrf functional successfully!";
	}
}
