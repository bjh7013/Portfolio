package com.ssazit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssazit.interceptor.AuthInterceptor;
import com.ssazit.interceptor.CORSInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	AuthInterceptor authInterceptor;
	@Autowired
	CORSInterceptor corsInterceptor;
	
	// 파일 업로드 가능 최대 사이즈
	private final int FILE_MAX_UPLOAD_SIZE = 10485760; // 1024 * 1024 * 10
	 
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
			.addPathPatterns("/user/mypage")
			.addPathPatterns("/user/pwcheck")
			.addPathPatterns("/user/modify")
			.addPathPatterns("/user/withdrawal")
			.addPathPatterns("/user/following")
			.addPathPatterns("/user/follower")
			.addPathPatterns("/user/f4f")
			.addPathPatterns("/user/searchlist/**")
			.addPathPatterns("/user/searchlistcount/**")
			.addPathPatterns("/user/block/**")
			.addPathPatterns("/user/follow/**")
			.addPathPatterns("/user/sendmsg/**")
			.addPathPatterns("/user/rcvmsg/**")
			.addPathPatterns("/user/rcvmsgcount")
			.addPathPatterns("/user/sendmsgcount")
			.addPathPatterns("/user/readmsg")
			.addPathPatterns("/user/upload")
			.addPathPatterns("/user/nickname")
			.addPathPatterns("/user/mynoti/**")
			.addPathPatterns("/blog/CUD")
			.addPathPatterns("/blog/myboard/CUD")
			.addPathPatterns("/blog/updateRootBoard")
			.addPathPatterns("/article/CUD")
			.addPathPatterns("/article/artcategory/CUD")
			.addPathPatterns("/article/acategory/CUD")
			.addPathPatterns("/article/category/CUD")
			.addPathPatterns("/article/articlelike/CUD")
			.addPathPatterns("/article/articleshare/CUD")
			.addPathPatterns("/article/articlefavorite/CUD")
			.addPathPatterns("/article/articlefavorite/CUD")
			.addPathPatterns("/article/articlefavorites")
			.addPathPatterns("/article/articles")
			.addPathPatterns("/book/CUD")
			.addPathPatterns("/book/bcontent/CUD")
			.addPathPatterns("/book/series/CUD")
			.addPathPatterns("/book/bookseries/CUD")
			.addPathPatterns("/book/booklike/CUD")
			.addPathPatterns("/book/bookfavorite/CUD")
			.addPathPatterns("/book/bookcategory/CUD")
			.addPathPatterns("/book/booktag/CUD")
			.addPathPatterns("/book/book/**")
			.addPathPatterns("/comment/CUD")
			.addPathPatterns("/comment/commentlike/CUD")
			.addPathPatterns("/comment/mycomments")
			.addPathPatterns("/comment/mybookcomments")
			.addPathPatterns("/comment/mybookcommentscount")
			.addPathPatterns("/comment/mycommentscount")
			.addPathPatterns("/comment/pick")
			.addPathPatterns("/tag/CUD")
			.addPathPatterns("/tag/tmptotag")
			.addPathPatterns("/tag/updatetaggroup")
			.addPathPatterns("/tag/arttag/CUD")
			.addPathPatterns("/tag/simtag/CUD")
			.addPathPatterns("/tag/tmptag/CUD")
			.addPathPatterns("/file/create_book_cover");
		
	}
	
}