package com.wuwo.maidan.wechat.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HomeService {

    protected String serviceUrl = "http://ORDER-SERVICE";

    @Autowired
    protected RestTemplate restTemplate;

	//这里默认不能用threadloacl 也就是db getconnect可能不能获取的同一个连接了
	//这里代码尽量简单就好 或者这样
//	@HystrixCommand(fallbackMethod = "stubMyService",
//			commandProperties = {
//					@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
//			}
//	)
	@HystrixCommand(fallbackMethod = "defaultHome")
	public String homeOrder() {
		return restTemplate.getForObject(serviceUrl + "/order/test",
				String.class);
	}

	public String defaultHome() {
		return "service error";
	}

}
