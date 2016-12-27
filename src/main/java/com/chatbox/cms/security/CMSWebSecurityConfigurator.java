/**
 * Description : This class used for handling permission to users.
 * 
 * @author Bhagawantha
 * @version 1.0
 * @see
 * @since Dec 04, 2016
 */

package com.chatbox.cms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Security Configurator to authenticate and authorize the url.
 *
 */
@Configuration
@EnableWebSecurity
public class CMSWebSecurityConfigurator extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println(" CMSWebSecurityConfigurator : configure starts");
		http.authorizeRequests()
				.antMatchers("/", "/scripts/**", "/resources/**", "/styles/**",
						"/home/**","/chatlogin/**").permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/admin/**")
		.access("hasRole('ROLE_ADMIN')").and().formLogin().loginProcessingUrl("/chatlogin/").successHandler(successHandler());
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		System.out
				.println(" CMSWebSecurityConfigurator : configureGlobal starts");
		auth.inMemoryAuthentication().withUser("user").password("user")
				.roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("nilesh").password("changeit")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("awantik").password("changeit")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("scott").password("tiger")
				.roles("USER");
		auth.inMemoryAuthentication().withUser("brian").password("lara")
				.roles("USER");
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		handler.setUseReferer(true);
		return handler;
	}

}
