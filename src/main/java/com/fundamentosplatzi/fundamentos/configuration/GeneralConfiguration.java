package com.fundamentosplatzi.fundamentos.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import com.fundamentosplatzi.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.fundamentos.pojo.UserPojo;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
@PropertySource("classpath:conection.properties") //Configurar archivo de propiedades nuevo
public class GeneralConfiguration {
	@Value("${value.name}")
	private String nombre;
	
	@Value("${value.apellido}")
	private String apellido;
	
	@Value("${value.random}")
	private String random;
	
	//Variable del nuevo archivo properties
	@Value("${jdbc.url}")
	private String jdbcurl;
	
	@Value("${driver}")
	private String driver;
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	
	@Bean
	public MyBeanWithProperties functionPr() {
		return new MyBeanWithPropertiesImplement(nombre, apellido);
	}
	
	@Bean
	public DataSource datasource(){
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driver);
		dataSourceBuilder.url(jdbcurl);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		
		return dataSourceBuilder.build();
	}
		
	
	
}
