package com.ivan.springcloud.usrmgt.feignclient;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("imageserver")
public interface ImageSeverClient {

	@RequestMapping(method = RequestMethod.GET, value = "/images/{userId}")
	public List<String> getUserImages(
			@PathVariable(value = "userId") String userId);
}
