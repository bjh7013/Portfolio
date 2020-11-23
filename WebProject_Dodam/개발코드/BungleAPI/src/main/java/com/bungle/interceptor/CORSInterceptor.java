package com.bungle.interceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CORSInterceptor  extends HandlerInterceptorAdapter{
	
	@Value("${access-control-allow-origin}")
	private String allowOrigin;
	
	@PostConstruct
	public void init() {
		System.out.println("=================================");
		System.out.println("allowOrigin = " + allowOrigin);
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CORS INTERCEPTOR==========================================");
		response.setHeader("Access-Control-Allow-Origin", "*");//와일드카드 x.
		System.out.println(response.getHeader("Access-Control-Allow-Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Headers", "content-type");
        response.addHeader("Access-Control-Allow-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "180");
   
		return true;
	}	
}