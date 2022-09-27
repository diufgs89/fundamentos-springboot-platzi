package com.fundamentosplatzi.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {
	private String nombre;
	private String apellido;
	
	
	public MyBeanWithPropertiesImplement(String nombre, String apellido) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.apellido = apellido;
	}
		
	
	@Override
	public String function() {
		// TODO Auto-generated method stub
		return nombre + " - " + apellido;
	}

}
