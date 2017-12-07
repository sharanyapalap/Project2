package com.niit.Backend.RestControllerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc

@ComponentScan("com.niit.Backend")

public class WebResolver {

	@Bean
	public InternalResourceViewResolver getViewResolver()
	{
		System.out.println("Resolver has been called....");
		
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		
		irvr.setPrefix("/WEB-INF/jsp");
		irvr.setSuffix(".jsp");
		return irvr;
		
		
	}
}
