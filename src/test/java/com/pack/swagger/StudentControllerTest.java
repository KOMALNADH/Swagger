package com.pack.swagger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.Student;
import com.pack.swagger.service.StudentServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StudentControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	StudentServiceImpl studentImpl;
	@MockBean
	StudentRepo studentRepo;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void testinsertStudent() throws Exception {
		Student s = new Student(13, "luffy", "pirate", 7036289111L);
		Mockito.when(studentRepo.save(s)).thenReturn(s);
		Mockito.when(studentImpl.InsertMovie(ArgumentMatchers.any())).thenReturn(s);
		String studentJson = mapper.writeValueAsString(s);// convert student to string
		MvcResult result = mockMvc
				.perform(post("/student/insertStudent").contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8").content(studentJson).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String res = result.getResponse().getContentAsString(); // convert response to string
		Student std = new ObjectMapper().readValue(res, Student.class);
		assertEquals((Integer) 13, std.getId());
	}

	@Test
	void testgetAllStudents() throws Exception {
		List<Student> slist = new ArrayList<>();
		slist.add(new Student(1, "komal", "cse", 8968587l));
		slist.add(new Student(2, "luffy", "pirate", 8675l));
		slist.add(new Student(3, "hygc", "gcf", 87567l));
		// Mockito.when(studentRepo.findAll()).thenReturn(slist);
		Mockito.when(studentImpl.getAllStudents()).thenReturn(slist);
		MvcResult result = mockMvc.perform(get("/student/studentsList")).andReturn();
		String res = result.getResponse().getContentAsString();
		List<Student> s = Arrays.asList(mapper.readValue(res, Student[].class));
		assertEquals(slist.size(), s.size());
	}

	@Test
	void testgetStudentById() throws Exception {
		Student s = new Student(10, "komal", "cse", 876576l);
		Mockito.when(studentImpl.findByStudentId(10)).thenReturn(s);
		MvcResult result = mockMvc.perform(get("/student/findbyid/10")).andReturn();
		String res = result.getResponse().getContentAsString();
		Student std = new ObjectMapper().readValue(res, Student.class);
		assertEquals(std.getId(), (Integer) 10);
	}

	@Test
	void testdeleteStudentById() throws Exception {
		Integer id=1;
		Student s=new Student(id,"komal","cse",8775l);
		when(studentRepo.findById(id)).thenReturn(Optional.of(s));
		mockMvc.perform(delete("/student/delete/{id}",id))
		       .andExpect(status().isOk());
		verify(studentImpl,times(1)).deleteStudent(id);
				

	}

	@Test
	void testfindByName() throws Exception {
		Student s = new Student(10, "luffy", "pirate", 8678554l);
		Mockito.when(studentImpl.findByStudentName("luffy")).thenReturn(s);
		MvcResult result = mockMvc.perform(get("/student/findbyname/luffy")).andReturn();
		String res = result.getResponse().getContentAsString();
		Student std = new ObjectMapper().readValue(res, Student.class);
		assertEquals(s.getId(), std.getId());
	}
	@Test
	void testUpdatDept() throws Exception {
		Student s=new Student(10, "luffy", "pirate", 8678554l);
		Mockito.when(studentImpl.InsertMovie(s)).thenReturn(s);
		 Mockito.when(studentImpl.findByStudentId(Mockito.anyInt())).thenReturn(s);//		MvcResult result = mockMvc.perform(put("/student/studentId/10/dept/mech")).andReturn();
//		String res = result.getResponse().getContentAsString();
//		Student std = new ObjectMapper().readValue(res, Student.class);
////		s.setDept("mech");
////		studentRepo.save(s);
//		assertEquals(std.getDept(),"mech");
		 Mockito.when(studentImpl.updateStudentById(10, "mech")).thenReturn(s);
		 String expectedJson = mapper.writeValueAsString(s);
         MvcResult result = mockMvc
 				.perform(put("/student/studentId/10/dept/mech").contentType(MediaType.APPLICATION_JSON)
 						.characterEncoding("utf-8").content(expectedJson).accept(MediaType.APPLICATION_JSON))
 				.andReturn();

         String res = result.getResponse().getContentAsString();
 		Student std = new ObjectMapper().readValue(res, Student.class);
//         assertThat(outputInJson).isEqualTo(expectedJson);
 		assertEquals(s.getId(),std.getId());
		
	}
	@Test
	void testUpdateStudent() throws Exception {
		Student s=new Student(10, "luffy", "pirate", 8678554l);
		Mockito.when(studentImpl.InsertMovie(s)).thenReturn(s);
		Mockito.when(studentImpl.findByStudentId(ArgumentMatchers.any())).thenReturn(s);
		Mockito.when(studentImpl.updateStudent(s)).thenReturn(s);
		String expectedJson = mapper.writeValueAsString(s);
		System.out.println(expectedJson);
		MvcResult result =  mockMvc
 				.perform(post("/student/update/10").contentType(MediaType.APPLICATION_JSON)
 						.characterEncoding("utf-8").content(expectedJson).accept(MediaType.APPLICATION_JSON))
 				.andReturn();
		String res = result.getResponse().getContentAsString();
		Student std = new ObjectMapper().readValue(res, Student.class);
		assertEquals(s.getDept(),std.getDept());
	}

}
