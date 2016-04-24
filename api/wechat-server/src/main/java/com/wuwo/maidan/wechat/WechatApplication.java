package com.wuwo.maidan.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableDiscoveryClient
//@ComponentScan({"com.wuwo.maidan.common","com.wuwo.maidan.wechat"})
public class WechatApplication {

	public static void main(String[] args) {

//		SpringApplication app = new SpringApplication(MySpringConfiguration.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);

//		new SpringApplicationBuilder()
//				.bannerMode(Banner.Mode.OFF)
//				.sources(Parent.class)
//				.child(Application.class)
//				.run(args);

		SpringApplication.run(WechatApplication.class, args);
	}
}
