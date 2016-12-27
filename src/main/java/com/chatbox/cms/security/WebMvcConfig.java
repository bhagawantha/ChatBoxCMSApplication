/**
 * Description : This class used for loading for loading static files. 
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 04, 2016
 */
package com.chatbox.cms.security;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/webapp/", "classpath:/static/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("WebMvcConfig : addResourceHandlers");
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(
					CLASSPATH_RESOURCE_LOCATIONS);
		}

	}

}
