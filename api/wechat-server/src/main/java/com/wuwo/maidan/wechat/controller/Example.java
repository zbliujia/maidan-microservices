package com.wuwo.maidan.wechat.controller;

import com.wuwo.maidan.common.utils.TestService;
import com.wuwo.maidan.wechat.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/wechat")
public class Example {

	@Autowired
	protected TestService testService;

	@Autowired
	protected HomeService homeService;

	@Autowired
	AnnotationConfigEmbeddedWebApplicationContext applicationContext;

	@Value("${testConfig.test}")
	String testValue;

	@RequestMapping("/home")
    Map<String,String> home(HttpServletRequest req, HttpServletResponse res) {
        Map<String,String> map = new HashMap<String,String>();
		if (req.getHeader("Test") != null) {
			res.addHeader("Test", req.getHeader("Test"));
		}
	    map.put("test-service",testService.getTest());
	    map.put("test-order",homeService.homeOrder());
	    map.put("wechat","123");
		int port = ((TomcatEmbeddedServletContainer)((AnnotationConfigEmbeddedWebApplicationContext)applicationContext).getEmbeddedServletContainer()).getPort();
		map.put("serverPort",port+"");
		map.put("UTF8","减肥");
		map.put("testValue",testValue);
	    return map;
    }

}
