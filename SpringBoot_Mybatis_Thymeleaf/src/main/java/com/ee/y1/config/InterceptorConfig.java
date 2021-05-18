package com.ee.y1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ee.y1.interceptor.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private TestInterceptor testInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//적용할 Interceptor bean을 등록
		registry.addInterceptor(testInterceptor)
		
		//add -> Interceptor를 적용할 URL 등록
//		.addPathPatterns("/notice/**")
		.addPathPatterns("/qna/**")
		
		//exclude -> Interceptor에서 제외할 URL 등록
		.excludePathPatterns("/notice/select");
		
		//어떤 URL 설정
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
