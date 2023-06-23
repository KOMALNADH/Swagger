package com.pack.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.Student;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepo studentRepo;
	@Override
	public Student InsertMovie(Student student) {
		return studentRepo.save(student);
		
	}
	@Override
	public List<Student> getAllStudents() {
		
		return studentRepo.findAll();
	}
	@Override
	public Student findByStudentId(Integer id) {
		Optional<Student> s=studentRepo.findById(id);
		Student std=null;
		if(s.isPresent()) {
		std= s.get() ;
		}
		return std;
		
		
	}
	@Override
	public void deleteStudent(Integer id) {
		 studentRepo.deleteById(id);
	}
	@Override
	public Student findByStudentName(String name) {
	
		return studentRepo.findByName(name);
	}
	@Override
	public Student updateStudent(Student student) {
		
		return studentRepo.save(student);
	}
	@Override
	public Student updateStudentById(Integer id, String dept) {
		Optional<Student> s=studentRepo.findById(id);
		Student std=null;
		if(s.isPresent()) {
			std= s.get() ;
			std.setDept(dept);
			studentRepo.save(std);
			}
		
		return std;
	}

}
