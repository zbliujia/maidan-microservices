package com.wuwo.maidan.edge;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class StaticResponseFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(StaticResponseFilter.class);

	@Override
	public String filterType() {
		return "pre";
		//return "static";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		//这里应该设置一个属性 然后其他的filter就都返回false了 就不要处理了
		RequestContext ctx = RequestContext.getCurrentContext();
		String test = ctx.getRequest().getParameter("test") ;
		return "1".equalsIgnoreCase(test);
	}

	@Override
	public Object run() {
		logger.info("-------------StaticResponseFilter");
		RequestContext ctx = RequestContext.getCurrentContext();
		// Set the default response code for static filters to be 200
		ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
		// first StaticResponseFilter instance to match wins, others do not set body and/or status
		if (ctx.getResponseBody() == null) {
			ctx.setResponseBody("暂时无法访问 啦啦啦");
			//ctx.setSendZuulResponse(false);
			//这里可以直接回复请求 做服务的降级和隔离 动态来
			ctx.sendZuulResponse();
			//ctx.setSendZuulResponse(false);
		}
		return null;
	}
}
