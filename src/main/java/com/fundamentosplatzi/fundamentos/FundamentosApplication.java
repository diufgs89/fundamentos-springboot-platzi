package com.fundamentosplatzi.fundamentos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.mapping.Array;
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
import com.fundamentosplatzi.fundamentos.service.UserService;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner{

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	
	private ComponentDependency componentDependency;
	private MyBean myBean;
	
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	
	private UserRepository userRepository;
	private UserService userService;
	
	//Iyesion de dependencia
	@Autowired 
	public FundamentosApplication(
				@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
				MyBean myBean,
				MyBeanWithDependency myBeanWithDependency,
				MyBeanWithProperties myBeanWithProperties,
				UserPojo userPojo,
				UserRepository userRepository,
				UserService userService) {
		
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//runEjemplosAnteriores();
		saveUserIdDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
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
		
		//QUERY_METHOD
		userRepository.findByName("Patricio")
		.stream()
		.forEach(user -> LOGGER.info("QUERY_METHOD" + user));
		
		LOGGER.info("QUERY_METHOD_EMAIL"+
		userRepository.findByEmailAndName("matilde@hotmail.com", "Matilde")
		.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
		//.forEach(user -> LOGGER.info("QUERY_METHOD_email" + user));
		
		LOGGER.info("Usuario NAME_PARAMETER "+
		userRepository.getAllBirthDateAndEmail(LocalDate.of(2021, 2, 1), "carmen@hotmail.com")
		.orElseThrow(() -> new RuntimeException("No se encontro usuario con NAME_PARAMETER"))
		);
		
	}
	
	public void saveWithErrorTransactional() {
		User test1 = new User("Test1","usertest1@hotmail.com",LocalDate.of(2021, 01, 01));
		User test2 = new User("Test2","usertest2@hotmail.com",LocalDate.of(2021, 02, 01));
		User test3 = new User("Test3","usertest3@hotmail.com",LocalDate.of(2020, 03, 01));
		User test4 = new User("Test4","usertest4@hotmail.com",LocalDate.of(2020, 04, 01));
		
		List<User> users = Arrays.asList(test1, test2, test3, test4);
		
		try {
			userService.saveTransactional(users);
		}catch(Exception e) {
			LOGGER.error("ERROR INSERT TEST ");
		}		
		
		userService.getAllUsers().stream()
		.forEach(user -> LOGGER.info("Usuario TEST : " + user));
		
	}

}
