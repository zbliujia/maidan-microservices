package com.wuwo.maidan.wechat.controller;

import com.wuwo.maidan.common.model.TestObject;
import com.wuwo.maidan.common.utils.TestService;
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

    protected String serviceUrl = "http://ORDER-SERVICE";

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

	@Autowired
	protected TestService testService;

    @RequestMapping("/home")
    Map<String,String> home() {
        Map<String,String> map = new HashMap<String,String>();
	    map.put("test-service",testService.getTest());
	    map.put("test-order",restTemplate.getForObject(serviceUrl + "/order/test",
                String.class));
	    map.put("wechat","123");
	    return map;
    }
}
