package com.ee.y1.config;


import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@Configuration
public class MessageLocaleConfig implements WebMvcConfigurer {
	
	//bean : 객체를 만들어 주는 것
	@Bean
	public LocaleResolver localeResolver() {
		
		//둘 중에 하나 선택해서 사용
		//session을 사용하여 설정
		SessionLocaleResolver sessionResolver = new SessionLocaleResolver();
		sessionResolver.setDefaultLocale(Locale.KOREAN);
		
		//cookie를 사용하여 설정
		CookieLocaleResolver cookieResolver = new CookieLocaleResolver();
		cookieResolver.setDefaultLocale(Locale.KOREA);
		cookieResolver.setCookieName("lang");
		
		return cookieResolver;
	}
	
	//Interceptor
	@Bean
	public LocaleChangeInterceptor localeChangeInterseptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		return changeInterceptor;
	}

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterseptor())
				.addPathPatterns("/**");
	}

	
	
}
