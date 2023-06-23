package com.pack.swagger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.swagger.model.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>{
	 public Student findByName(String name);
}
