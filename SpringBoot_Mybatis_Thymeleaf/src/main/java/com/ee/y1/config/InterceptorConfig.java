package com.ee.y1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ee.y1.interceptor.TestInterceptor;

public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private TestInterceptor testInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//Interceptor bean을 등록
		registry.addInterceptor(testInterceptor)
		.addPathPatterns("/notice/**");
		
		//어떤 URL 설정
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
