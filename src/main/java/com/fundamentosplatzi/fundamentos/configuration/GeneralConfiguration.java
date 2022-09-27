package com.fundamentosplatzi.fundamentos.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.fundamentosplatzi.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.fundamentos.pojo.UserPojo;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
	@Value("${value.name}")
	private String nombre;
	
	@Value("${value.apellido}")
	private String apellido;
	
	@Value("${value.random}")
	private String random;
	
	
	@Bean
	public MyBeanWithProperties functionPr() {
		return new MyBeanWithPropertiesImplement(nombre, apellido);
	}
	
	@Bean
	public DataSource datasource(){
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:mem:testdb");
		dataSourceBuilder.username("SA");
		dataSourceBuilder.password("");
		
		return dataSourceBuilder.build();
	}
		
	
	
}
