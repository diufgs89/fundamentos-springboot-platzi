package com.fundamentosplatzi.fundamentos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.fundamentosplatzi.fundamentos.bean.MyBean;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.fundamentos.repository.UserRepository;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner{

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	
	private ComponentDependency componentDependency;
	private MyBean myBean;
	
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	
	private UserRepository userRepository;
	
	//Iyesion de dependencia
	@Autowired 
	public FundamentosApplication(
				@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
				MyBean myBean,
				MyBeanWithDependency myBeanWithDependency,
				MyBeanWithProperties myBeanWithProperties,
				UserPojo userPojo,
				UserRepository userRepository) {
		
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//runEjemplosAnteriores();
		saveUserIdDataBase();
		getInformationJpqlFromUser();
	}
	
	private void runEjemplosAnteriores() {
		// Usar dependencia y su metodo saludar
		componentDependency.saludar();
		// Usar dependencia y su metodo implementado print
		myBean.print();
		myBeanWithDependency.prinWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword() + " - " + userPojo.getAge());
		
		try {
			int valor = 10 / 0;
			LOGGER.debug("VALOR "+ valor);
		} catch(Exception e) {
			LOGGER.error("Esto es un error del APLICATIVO"+ e.getMessage());
		}
	}
	
	private void saveUserIdDataBase() {
		User user1 = new User("Dago","dago@hotmail.com",LocalDate.of(2021, 01, 01));
		User user2 = new User("Carmen","carmen@hotmail.com",LocalDate.of(2021, 02, 01));
		User user3 = new User("Patricio","patricio@hotmail.com",LocalDate.of(2021, 03, 01));
		User user4 = new User("Matilde","matilde@hotmail.com",LocalDate.of(2021, 04, 01));
		User user5 = new User("Manastacio","anasta@hotmail.com",LocalDate.of(2021, 05, 01));
		
		List<User> listaUsuarios = Arrays.asList(user1,user2,user3,user4,user5);
		listaUsuarios.stream().forEach(userRepository::save);
		
	}
	
	private void getInformationJpqlFromUser() {
		//Consultar por email
		LOGGER.info("Usausrio buscado email : "+ 
				userRepository.findByUserEmail("patricio@hotmail.com")
				.orElseThrow(() -> new RuntimeException("No encontrado")));		
		
		//Consultar por nombre coincide, y ordenar por ID desc
		userRepository.findAndSort("Ma", Sort.by("id").descending())
				.stream().forEach( user -> LOGGER.info("Usuarios encontrados : "+ user));
		//.orElseThrow(() -> new RuntimeException("No encontrado")));
		
	}

}
