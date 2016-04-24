package com.wuwo.maidan.wechat.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HomeService {

    protected String serviceUrl = "http://ORDER-SERVICE";

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "defaultHome")
	public String homeOrder() {
		return restTemplate.getForObject(serviceUrl + "/order/test",
				String.class);
	}

	public String defaultHome() {
		return "service error";
	}

}
