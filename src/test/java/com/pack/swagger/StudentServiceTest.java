package com.pack.swagger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.Student;
import com.pack.swagger.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
	@Autowired
	StudentService studentService;
	@Autowired
	StudentRepo studentRepo;
	
	
	@Test
	void testDeleteStudent() {
		Student saveIntoDb=new Student(1,"eee","mahi",8686l);
		studentRepo.save(saveIntoDb);
		studentService.deleteStudent(saveIntoDb.getId());
		List<Student> getAllStudentsFromDb=studentRepo.findAll();
		assertEquals(getAllStudentsFromDb.size(),5);
		
		
	}
	@Test
	void testFindByStudentName() {
		Student saveIntoDb=new Student(1,"eee","mahi",8686l);
		studentRepo.save(saveIntoDb);
		Student getFromDb=studentService.findByStudentName(saveIntoDb.getName());
		assertEquals(getFromDb.getName(),saveIntoDb.getName());
	}
	@Test
	void testFindByStudentId() {
		Student saveIntoDb=new Student(1,"eee","mahi",8686l);
		studentRepo.save(saveIntoDb);
		Student getFromDb=studentService.findByStudentId(saveIntoDb.getId());
		assertEquals(getFromDb.getName(),saveIntoDb.getName());
	}
	@Test
	void testSaveStudent() {
		Student s=new Student(41,"komal","cse",9981234l);
		Student saveStudent=studentService.InsertMovie(s);
		Optional opt= studentRepo.findById(saveStudent.getId());
		Student s1=(Student)opt.get();
		assertEquals(s.getId(),s1.getId());
	}
	@Test
	void testUpdateDept() {
		Optional<Student> s=studentRepo.findById(41);
		Student std=s.get();
		Student student=studentService.updateStudentById(41, "king");
		 assertEquals(student.getDept(),"king");
	}
	@Test
	void testgetAllStudents() {
		List<Student> list=studentService.getAllStudents();
		List<Student> std=new ArrayList<>();
		for(Student s:list) {
			std.add(s);
		}
		assertEquals(list.size(),std.size());
	}
	@Test
	void testUpdateStudent() {
		Optional<Student> s=studentRepo.findById(41);
		Student std=s.get();
		std.setDept("king");
		std.setName("komalnadh");
		Student std1=studentService.updateStudent(std);
		assertEquals(std1.getName(),"komalnadh");
	}
	
}



