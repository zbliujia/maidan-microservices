package com.wuwo.maidan.wechat.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wuwo.maidan.common.model.TestObject;
import com.wuwo.maidan.common.utils.TestService;
import com.wuwo.maidan.wechat.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class Example {

	@Autowired
	protected TestService testService;

	@Autowired
	protected HomeService homeService;

	@RequestMapping("/home")
    Map<String,String> home() {
        Map<String,String> map = new HashMap<String,String>();
	    map.put("test-service",testService.getTest());
	    map.put("test-order",homeService.homeOrder());
	    map.put("wechat","123");
	    return map;
    }

}
