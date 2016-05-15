package com.wuwo.maidan.wechat.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

	//然后 还需要处理set-cookie 返回的东西 设置到我的response里面去
	@HystrixCommand(fallbackMethod = "defaultHome1")
	public String homeOrder1(HttpServletRequest req) {
		HttpHeaders requestHeaders = new HttpHeaders();
		Cookie[] cookies = req.getCookies();
		String strCookies = "";
		if (cookies != null){
			for (int i = 0 ; i < cookies.length ; i++){
				if (cookies[i].getName().equalsIgnoreCase("SESSION")){
					strCookies = "SESSION="+cookies[i].getValue();
					break;
				}
			}
		}
		requestHeaders.add("Cookie", strCookies);
		HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
		return restTemplate.exchange(serviceUrl + "/order/test",
				HttpMethod.GET,
				requestEntity,
				String.class).getBody();
	}

	public String defaultHome() {
		return "service error";
	}

	public String defaultHome1(HttpServletRequest req) {
		return "service error";
	}

}
