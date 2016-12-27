/**
 * Description : This class used for initiating the Spring Boot Application.
 * 
 * @author Bhagawantha Parasuraman
 * @version 1.0
 * @see
 * @since Dec 01, 2016
 */
package com.chatbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ChatboxCMSApplication extends
		SpringBootServletInitializer {

	/**
	 * 
	 * 
	 * Adding Web application configuration.
	 * 
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ChatboxCMSApplication.class);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ChatboxCMSApplication.class, args);
	}

}
