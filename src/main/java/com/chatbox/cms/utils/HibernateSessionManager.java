/**
 * This class is for Database session configuration.
 * 
 * @author Bhagawantha Parasuraman
 *
 */
package com.chatbox.cms.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.chatbox.cms.constants.CommonConstants;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = CommonConstants.COMPONENT_SCAN)
public class HibernateSessionManager {

	/**
	 * This method configures session factory.
	 * 
	 * @return
	 */
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(
				this.datasource());
		localSessionFactoryBuilder.scanPackages(CommonConstants.ENTITY_SCAN)
				.addProperties(getHibernateProperties());
		return localSessionFactoryBuilder.buildSessionFactory();
	}

	/**
	 * This method configures datasource.
	 * 
	 * @return
	 */
	@Bean
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/chatbox");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	/**
	 * This method configures the hibernate properties.
	 * 
	 * @return
	 */
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return prop;
	}

	/**
	 * This method is used for transaction management.
	 * 
	 * @return
	 */
	@Bean
	public HibernateTransactionManager txManager() {
		return new HibernateTransactionManager(sessionFactory());
	}

}
