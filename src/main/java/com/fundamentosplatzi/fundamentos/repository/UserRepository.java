package com.fundamentosplatzi.fundamentos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fundamentosplatzi.fundamentos.dto.UserDto;
import com.fundamentosplatzi.fundamentos.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//JPQL
	@Query("SELECT u FROM User u WHERE u.email=?1")
	Optional<User> findByUserEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE ?1%")
	List<User> findAndSort(String name, Sort sort);
	
	
	//QUERY_METODOS
	List<User> findByName(String name);
	
	Optional<User> findByEmailAndName(String email, String name);
	
	
	
	//----------
	@Query("SELECT new com.fundamentosplatzi.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)"
			+ "FROM User u "
			+ "WHERE u.birthDate=:parametroFecha "
			+ "and u.email=:parametroEmail ")
	Optional<UserDto> getAllBirthDateAndEmail(@Param("parametroFecha") LocalDate date, 
												@Param("parametroEmail") String email);
	

}
