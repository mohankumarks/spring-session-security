package com.mohan.security.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.mohan.security.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan(new String[] { "com.mohan.security.model" });

		return sessionFactory;
	}

	private DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("app.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("app.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("app.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("app.jdbc.password"));

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("app.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("app.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("app.hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("app.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.cache.use_second_level_cache",
				environment.getRequiredProperty("app.hibernate.cache.use_second_level_cache"));
		properties.put("hibernate.cache.use_query_cache",
				environment.getRequiredProperty("app.hibernate.cache.use_query_cache"));
		properties.put("hibernate.cache.region.factory_class",
				environment.getRequiredProperty("app.hibernate.cache.region.factory_class"));
		properties.put("javax.persistence.sharedCache.mode",
				environment.getRequiredProperty("app.javax.persistence.sharedCache.mode"));

		return properties;
	}

	@Autowired
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);

		return transactionManager;
	}
}
