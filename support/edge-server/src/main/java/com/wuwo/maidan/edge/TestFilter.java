package com.wuwo.maidan.edge;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class TestFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(TestFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 98;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		logger.info("-------------TestFilter");
		RequestContext ctx = RequestContext.getCurrentContext();
		Cookie[] cookies = ctx.getRequest().getCookies();
		//需要做一些cookie相关的utils那些设置进去 那些不设置进去 这样的 不能老这么for循环啊
		//当然也可以不用这样的形式 每次都带上token的方式 这样就不知道是否可以直接用 spring session里 是否需要自己做redis的session管理
		if (cookies != null) {
			String strCookies = "";
			for (int i = 0 ; i < cookies.length ; i++){
				if (strCookies.length() > 0) {
					strCookies += "; ";
				}
				strCookies += cookies[i].getName()+"="+cookies[i].getValue();
			}
			ctx.addZuulRequestHeader("Cookie",strCookies);
		}
		ctx.addZuulRequestHeader("Test", "TestSample");
		return null;
	}
}
