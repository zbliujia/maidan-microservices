package com.wuwo.maidan.monitordashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
//import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
@Controller
public class HystrixDashboardApplication extends SpringBootServletInitializer {
	
	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HystrixDashboardApplication.class).web(true);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixDashboardApplication.class).web(true).run(args);
	    //SpringApplication.run(HystrixDashboardApplication.class, args);
    }

}