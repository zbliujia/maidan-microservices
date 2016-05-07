package com.wuwo.maidan.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan({"com.wuwo.maidan.common.utils","com.wuwo.maidan.edge"})
public class ZuulApplication {

    public static void main(String[] args) {
        //new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
	    SpringApplication.run(ZuulApplication.class, args);
    }

//	@Bean
//	public TestFilter testFilter() {
//		return new TestFilter();
//	}
//
//	@Bean
//	public SurgicalDebugFilter surgicalDebugFilter() {
//		return new SurgicalDebugFilter();
//	}

}
