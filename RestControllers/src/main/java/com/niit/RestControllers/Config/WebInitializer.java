package com.niit.RestControllers.Config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	protected Class<?>[] getRootConfigClasses() {

		return new Class[]{WebResolver.class};
	}

	
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}

}
