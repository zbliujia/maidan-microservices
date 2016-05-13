package com.wuwo.maidan.edge;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
		ctx.addZuulRequestHeader("Test", "TestSample");
		return null;
	}
}
