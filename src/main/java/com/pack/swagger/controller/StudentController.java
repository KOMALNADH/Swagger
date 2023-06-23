package com.pack.swagger.controller;

import java.util.List;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.swagger.model.Student;
import com.pack.swagger.service.StudentService;



@RestController
@RequestMapping(value="/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	protected final static org.apache.juli.logging.Log logger=LogFactory.getLog(StudentController.class);
	/**
	 * 
	 * @param student
	 * @return
	 */
	@PostMapping(value="/insertStudent", consumes="application/json")
	public Student insertStudent(@RequestBody Student student) {
		logger.info("inserting student");
		return studentService.InsertMovie(student);
	}
	/**
	 * 
	 * @return
	 */
	@GetMapping(value="/studentsList",produces="application/json")
	public List<Student> allStudents(){
		return studentService.getAllStudents();
		
	}
//	/**
//	 * 
//	 * @param id
//	 * @param student
//	 * @return
//	 */
	@PostMapping(value="/update/{id}",consumes="application/json")
	public Student updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		
			Student s=studentService.findByStudentId(id);
			s.setDept(student.getDept());
			s.setName(student.getName());
			s.setPhone_No(student.getPhone_No());
			return studentService.updateStudent(s);
			
			
		
		
		
	}
//	/**
//	 * 
//	 * @param id
//	 * @param student
//	 */
	@DeleteMapping(value="/delete/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		 studentService.deleteStudent(id);
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	public Student findById(@PathVariable Integer id) {
		return studentService.findByStudentId(id);
		
	}
	/**
	 */
	@GetMapping("/findbyname/{name}")
	public Student findByName(@PathVariable String name) {
		return studentService.findByStudentName(name);
	}
	
	@PutMapping(value="/studentId/{Id}/dept/{newdept}",produces="application/json")
    public Student updateDepartment(@PathVariable("Id")Integer Id,@PathVariable("newdept")String newdept){
			Student s=studentService.findByStudentId(Id);
			Student std=studentService.updateStudentById(Id,newdept);
                   return std;

    }
	

}
