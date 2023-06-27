package com.pack.swagger.controller;

import java.util.List;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;
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
	public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
		logger.info("inserting student");
		Student std= studentService.InsertMovie(student);
			return new ResponseEntity<>(std,HttpStatus.CREATED);
		
	}
	/**
	 * 
	 * @return
	 */
	@GetMapping(value="/studentsList",produces="application/json")
	public ResponseEntity<List<Student>> allStudents(){
		List<Student> list=studentService.getAllStudents();
		
		return new ResponseEntity<>(list,HttpStatus.OK); 
		
	}
//	/**
//	 * 
//	 * @param id
//	 * @param student
//	 * @return
//	 */
	@PostMapping(value="/update/{id}",consumes="application/json")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		
			Student s=studentService.findByStudentId(id);
			s.setDept(student.getDept());
			s.setName(student.getName());
			s.setPhone_No(student.getPhone_No());
			Student stud=studentService.updateStudent(s);
			return new ResponseEntity<>(stud,HttpStatus.CREATED);
			
			
		
		
		
	}
//	/**
//	 * 
//	 * @param id
//	 * @param student
//	 */
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Integer id) {
		 studentService.deleteStudent(id);
		 return new ResponseEntity<>(HttpStatus.OK);
		
		
		
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/findbyid/{id}")
	public ResponseEntity<Student> findById(@PathVariable Integer id) {
		Student student=studentService.findByStudentId(id);
		
			return new ResponseEntity<>(student,HttpStatus.OK);
				
		
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	
	@GetMapping("/findbyname/{name}")
	public ResponseEntity<Student> findByName(@PathVariable String name) {
		Student student= studentService.findByStudentName(name);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	/**
	 * 
	 * @param Id
	 * @param newdept
	 * @return
	 */
	@PutMapping(value="/studentId/{Id}/dept/{newdept}",produces="application/json")
    public ResponseEntity<Student> updateDepartment(@PathVariable("Id")Integer id,@PathVariable("newdept")String newdept){
//			Student s=studentService.findByStudentId(Id);
			Student std=studentService.updateStudentById(id,newdept);
                   return new ResponseEntity<>(std,HttpStatus.OK);

    }
	@GetMapping(value="/studentCount",produces="application/json")
	public ResponseEntity<List<CountForDept>> student(){
		List<CountForDept> list=studentService.getStudentCount();
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	@GetMapping(value="/studentAndId",produces="application/json")
	public ResponseEntity<List<StudentIdName>> studentWithId(){
		List<StudentIdName> list=studentService.getStudentWithId();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

}
