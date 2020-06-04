package athena.springcloud.zuul.application.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AccessFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURL().toString());
		
		//验证权限
		if (request.getParameter("token") == null) {
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			context.setResponseBody("auth error");
			return null;
		}
		return null;
	}

}
