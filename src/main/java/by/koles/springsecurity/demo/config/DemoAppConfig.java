package by.koles.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.koles.springsecurity.demo")
@PropertySource("classpath:persistence-postgresql.properties")
public class DemoAppConfig {
	
	//set up variable to hold the properties
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	//define a Bean for security datasource
	@Bean
	public DataSource securityDataSource() {		
		//create connection pool
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		
		//set the jdbc driver class
		try {
			pooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		//log the connection props
		logger.info("======= jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info("======= jdbc.user=" + env.getProperty("jdbc.user"));	
		
		//set database connection properties
		pooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		pooledDataSource.setUser(env.getProperty("jdbc.user"));
		pooledDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool properties
		pooledDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		pooledDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		pooledDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		pooledDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		//pooledDataSource.setPreferredTestQuery("SET SEARCH_PATH TO security");
		//pooledDataSource.setTestConnectionOnCheckout(true);

	/*	Connection connection = null;
		try {
		    connection = pooledDataSource.getConnection();
		    Statement st = connection.createStatement();
		    //выполняем sql запрос
		    st.execute( "SET SEARCH_PATH TO security" );		    
		    	
		} catch (Exception e) {
		    e.printStackTrace();
		}finally {
			// "Вернуть" (закрыть) подключение
			try {
			    connection.close();		
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}*/
		
		return pooledDataSource;
	}
	
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
	
}




















