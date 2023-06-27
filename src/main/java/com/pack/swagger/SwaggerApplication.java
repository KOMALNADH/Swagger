package com.pack.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.StudentIdName;

import java.util.*
;@SpringBootApplication
public class SwaggerApplication implements CommandLineRunner{
	@Autowired
	StudentRepo studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//customJpaMethod();
		countForDept();
		
	}

	private void countForDept() {
		List<CountForDept> list=studentRepo.findCountByDept();
		List<CountForDept> list1=new ArrayList<>();
		list.forEach(list1::add);
		list1.forEach(System.out::println);
		
	}

	private void customJpaMethod() {
		List<StudentIdName> list=studentRepo.findNameId();
		list.forEach(System.out::println);
		
	}

}
