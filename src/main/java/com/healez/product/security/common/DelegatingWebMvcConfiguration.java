package com.healez.product.security.common;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport  {

	@Bean
	public DeviceResolverHandlerInterceptor 
	        deviceResolverHandlerInterceptor() {
	    return new DeviceResolverHandlerInterceptor();
	}

	@Bean
	public DeviceHandlerMethodArgumentResolver 
	        deviceHandlerMethodArgumentResolver() {
	    return new DeviceHandlerMethodArgumentResolver();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(deviceResolverHandlerInterceptor());
	}

	@Override
	public void addArgumentResolvers(
	       List<HandlerMethodArgumentResolver> argumentResolvers) {
	   argumentResolvers.add(deviceHandlerMethodArgumentResolver());
	}
	
	
}
