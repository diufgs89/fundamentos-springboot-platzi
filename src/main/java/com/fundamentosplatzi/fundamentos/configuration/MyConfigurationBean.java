package com.fundamentosplatzi.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fundamentosplatzi.fundamentos.bean.MyBean;
import com.fundamentosplatzi.fundamentos.bean.MyBean2Implement;
import com.fundamentosplatzi.fundamentos.bean.MyBeanImplement;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithDependencyImplement;
import com.fundamentosplatzi.fundamentos.bean.MyOperation;
import com.fundamentosplatzi.fundamentos.bean.MyOperationImplement;

@Configuration
public class MyConfigurationBean {
	
	@Bean
	public MyBean beanOperation() {		
		//return new MyBeanImplement();
		return new MyBean2Implement();
	}
	
	@Bean
	public MyOperation beanOperationSum() {		
		//return new MyBeanImplement();
		return new MyOperationImplement();
	}
	
	@Bean
	public MyBeanWithDependencyImplement beanOperationWithDependency(MyOperation myOperation) {		
		//return new MyBeanImplement();
		return new MyBeanWithDependencyImplement(myOperation);
	}
}
