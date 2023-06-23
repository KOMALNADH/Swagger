package com.pack.swagger.service;

import java.util.List;

import com.pack.swagger.model.Student;

public interface StudentService {
	public Student InsertMovie(Student student);
	public List<Student> getAllStudents();
	public Student findByStudentId(Integer id);
	public void deleteStudent(Integer id);
	public Student findByStudentName(String name);
	public Student updateStudent(Student student);
	public Student updateStudentById(Integer id,String dept);
}
