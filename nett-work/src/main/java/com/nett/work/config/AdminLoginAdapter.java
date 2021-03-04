package com.nett.work.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nett.work.interceptor.AdminLoginInterceptor;
import com.nett.work.interceptor.InParamInterceptor;

@Configuration
public class AdminLoginAdapter implements  WebMvcConfigurer {
	@Autowired
	AdminLoginInterceptor adminLoginInterceptor;
	@Autowired
	InParamInterceptor inParamInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		InterceptorRegistration registration1 = registry.addInterceptor(adminLoginInterceptor)
//				.addPathPatterns("/*").excludePathPatterns("/login/**");
		InterceptorRegistration registration2 = registry.addInterceptor(inParamInterceptor).addPathPatterns("/*")
				.excludePathPatterns("/login/**");
	}

}