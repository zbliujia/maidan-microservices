package com.wuwo.maidan.wechat.controller;

import com.wuwo.maidan.common.utils.TestService;
import com.wuwo.maidan.wechat.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/wechat")
public class SessionExample {

	@Autowired
	protected HomeService homeService;

	@RequestMapping("/setSession1")
    String setSession1(@RequestParam(value="session") String session, HttpServletRequest req, HttpServletResponse res) {
//		req.getCookies()
//
//		Cookie:_ga=GA1.1.1069527752.1459145384; SESSION=1ebabd28-e06b-4d24-b57e-9e1618a8876c
		req.getSession().setAttribute("testSession",session);
        return "set session to "+session+" "+homeService.homeOrder1(req);
    }

	@RequestMapping("/getSession1")
	String getSession1(HttpServletRequest req, HttpServletResponse res) {
		return "session is "+req.getSession().getAttribute("testSession")+" "+homeService.homeOrder1(req);
	}

	@RequestMapping("/setSession")
	String setSession(@RequestParam(value="session") String session, HttpServletRequest req, HttpServletResponse res) {
//		req.getCookies()
//
//		Cookie:_ga=GA1.1.1069527752.1459145384; SESSION=1ebabd28-e06b-4d24-b57e-9e1618a8876c
		req.getSession().setAttribute("testSession",session);
		return "set session to "+session+" "+homeService.homeOrder();
	}

	@RequestMapping("/getSession")
	String getSession(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0 ; i < cookies.length ; i++){
				System.out.printf(cookies[i].getName());
			}
		}
		return "session is "+req.getSession().getAttribute("testSession")+" "+homeService.homeOrder();
	}

}
