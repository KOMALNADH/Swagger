package com.pack.swagger.service;

import java.util.List;

import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;

public interface StudentService {
	public Student InsertMovie(Student student);
	public List<Student> getAllStudents();
	public Student findByStudentId(Integer id);
	public void deleteStudent(Integer id);
	public Student findByStudentName(String name);
	public Student updateStudent(Student student);
	public Student updateStudentById(Integer id,String dept);
	public List<CountForDept> getStudentCount();
	public List<StudentIdName> getStudentWithId();
}
