package com.niit.Backend.Config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Backend.Dao.BlogDao;
import com.niit.Backend.Dao.UserDao;
import com.niit.Backend.DaoImpl.BlogDaoImpl;
import com.niit.Backend.DaoImpl.UserDaoImpl;
import com.niit.Backend.Model.Blog;
import com.niit.Backend.Model.UserDet;


@Configuration
@EnableTransactionManagement
@ComponentScan("com.niit.Backend")

public class DBConfig 
{
	   //1.Creating a DataSource Object which is used for LocalSessionFactory
		public DataSource getOracleDataSource()
		{
			DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
			driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
			driverManagerDataSource.setUsername("sharanya");
			driverManagerDataSource.setPassword("sharanya");
			return driverManagerDataSource;
		}
		
		//2.Creating Hibernate Properties which is used by LocalSessionFactory
		public Properties getHibernateProperties()
		{
			Properties properties=new Properties();
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
			return properties;
		}
		
		@Bean
		public SessionFactory getSessionFactory()
		{
			LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(getOracleDataSource());
			localSessionFactoryBuilder.addProperties(getHibernateProperties());
			localSessionFactoryBuilder.addAnnotatedClass(Blog.class);
			localSessionFactoryBuilder.addAnnotatedClass(UserDet.class);
			System.out.println("SessionFactory Bean Created");
			return localSessionFactoryBuilder.buildSessionFactory();
		}
		
		@Bean
		public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
		{
			return new HibernateTransactionManager(sessionFactory);
		}
		
		@Bean
		public BlogDao getBlogDAO(SessionFactory sessionFactory)
		{
			System.out.println("Blog DAO object Created");
			return new BlogDaoImpl(sessionFactory);
		}
		
		@Bean
		public UserDao getUserDAO(SessionFactory sessionFactory)
		{
			System.out.println("User DAO object Created");
			return new UserDaoImpl(sessionFactory);
		}
}