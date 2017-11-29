package com.niit.Backend.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Backend.Model.Blog;
import com.niit.Backend.Model.Forum;
import com.niit.Backend.Model.UserDet;

@Configuration
@ComponentScan("com.niit.Backend")
@EnableTransactionManagement

public class DBConfig {

	
	@Bean(name ="dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("sharanya");
		dataSource.setPassword("sharanya");
		
		return dataSource;
	}
	
	private Properties getHibernateProperties()
	{
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return properties;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(UserDet.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
	
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	
}