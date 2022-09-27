package com.fundamentosplatzi.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

	Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
	
	private MyOperation myOperation;
	
	@Autowired
	public MyBeanWithDependencyImplement(MyOperation myOperation) {
		// TODO Auto-generated constructor stub
		this.myOperation = myOperation;
	}
	
	
	@Override
	public void prinWithDependency() {
		// TODO Auto-generated method stub
		LOGGER.info("Hemos ingresado al metodo prinWithDependency");
		int numero = 5;
		LOGGER.debug("El nunmero enviado como parametro a la imp myOperation "+ numero);
		System.out.println("Numero:"+ myOperation.sum(numero));
		System.out.println("Holam mundo BEAN DEPENDENCY");
	}

}
