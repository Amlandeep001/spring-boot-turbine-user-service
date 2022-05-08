package com.springboot.TurbineUserService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.TurbineUserService.client.UserClient;
import com.springboot.TurbineUserService.pojo.Product;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserRestController {

	private final UserClient userClient;

	UserRestController(UserClient userClient) {
		this.userClient = userClient;

	}

	@GetMapping(value = "/personalized/{id}")
	@HystrixCommand(fallbackMethod = "recommendationFallback")
	public Product[] personalized(@PathVariable int id) {
		Product[] result = userClient.getRecommendedProductList();
		return result;
	}

	public Product[] recommendationFallback(int id) {
		log.info("into recommendationFallback method========= id: {}", id);
		return new Product[0];
	}

}
