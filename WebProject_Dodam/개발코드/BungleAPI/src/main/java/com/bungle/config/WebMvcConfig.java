package com.bungle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bungle.interceptor.AuthInterceptor;
import com.bungle.interceptor.CORSInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	AuthInterceptor authInterceptor;
	@Autowired
	CORSInterceptor corsInterceptor;
	
	// 파일 업로드 가능 최대 사이즈
	private final int FILE_MAX_UPLOAD_SIZE = 10485760; // 1024 * 1024 * 10
	 

	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addMapping("/**")
////				.allowedOrigins("http://localhost:8080")
////				.allowedOrigins("http://localhost:8081")
//				.allowedOrigins("http://k3d206.p.ssafy.io")
//				.allowCredentials(true);
//				
//	}

	@Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(FILE_MAX_UPLOAD_SIZE); 
        return multipartResolver;
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(corsInterceptor)
				.addPathPatterns("/**");
		
		registry.addInterceptor(authInterceptor)
		.addPathPatterns("/user/reget")
		.addPathPatterns("/user/info")
		.addPathPatterns("/user/myinfo")
		.addPathPatterns("/user/child/**")
		.addPathPatterns("/user/makeparent/**")
		.addPathPatterns("/user/childreninfo")
		.addPathPatterns("/user/userstatus")
		.addPathPatterns("/class/userclass")
		.addPathPatterns("/class/class")
		.addPathPatterns("/class/imgclass")
		.addPathPatterns("/class/oneclass")
		.addPathPatterns("/class/oneclass/**")
		.addPathPatterns("/class/allclass")
		.addPathPatterns("/class/joincall")
		.addPathPatterns("/class/checkjoin")
		.addPathPatterns("/class/classjoin")
		.addPathPatterns("/class/outclass/**")
		.addPathPatterns("/classtime/searchclasstime/**")
		.addPathPatterns("/dictation/createproblem")
		.addPathPatterns("/dictation/dictation")
		.addPathPatterns("/dictation/dictationset")
		.addPathPatterns("/dictationsubmit/dictationsubmit")
		.addPathPatterns("/dictationsubmit/dictationsubmituserno")
		.addPathPatterns("/wordtestsubmit/wordtestsubmit")
		.addPathPatterns("/wordtest/userword")
		.addPathPatterns("/koreandetail/score")
		.addPathPatterns("/wordtest/wordset")
		.addPathPatterns("/chataudio/**");

		
	}
	
}