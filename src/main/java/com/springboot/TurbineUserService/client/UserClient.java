package com.springboot.TurbineUserService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.TurbineUserService.pojo.Product;


@FeignClient(value="recommendation-service", url="http://localhost:3333" )
public interface UserClient {
	
	@GetMapping("/recommendations")
	public Product[] getRecommendedProductList();

}
