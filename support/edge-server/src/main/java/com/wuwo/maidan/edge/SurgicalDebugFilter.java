package com.wuwo.maidan.edge;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;

import com.netflix.zuul.context.RequestContext;
import com.wuwo.maidan.common.utils.HttpUtils;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class SurgicalDebugFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(SurgicalDebugFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 99;
	}

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Autowired
	protected HttpUtils restTemplate;

	@Value("${eureka.client.serviceUrl.defaultZone}")
	private String eurekaBaseUrl;

	@Override
	public Object run() {
		logger.info("-------------SurgicalDebugFilter");
		try {
			//String json = restTemplate.getForObject(eurekaBaseUrl+"/apps/wechat",String.class);
			String json = restTemplate.sendGet("http://127.0.0.1:5555/eureka/apps/wechat");
			JSONObject jsonObject = JSON.parseObject(json).getJSONObject("application");
			JSONArray instances = jsonObject.getJSONArray("instance");
			if (instances.size() > 1){
				JSONObject instance = instances.getJSONObject(0);
				//这里的端口可以从redis中获取
				RequestContext.getCurrentContext().setRouteHost(new URL(instance.getString("homePageUrl")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
