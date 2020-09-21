package com.neosoft.webflux.reactive;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.neosoft.webflux.reactive.model.Student;
import com.neosoft.webflux.reactive.restcontroller.StudentRestController;
import com.neosoft.webflux.reactive.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(StudentRestController.class)
public class StudentRestControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	Student std = new Student(1, "Rakesh", "NMU", 9.8, "Pass");
	
	@MockBean
	private StudentService stdService;
	

	@Test
	public void createStudent() {

		Mono<Student> studentMono = Mono.just(std);
		when(stdService.createStudent(std)).thenReturn(studentMono);

		webTestClient.post().uri("/student/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(std), Student.class).exchange().expectStatus()
				.isOk();

	}

	@Test
	public void testGetStudentById() {
		Mono<Student> studentMono = Mono.just(std);

		when(stdService.getStudentById(1)).thenReturn(studentMono);

		webTestClient.get().uri("/student/get/1").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBody(Student.class).value(std1 -> std1.getStdName(), equalTo("Rakesh"))
				.value(std1 -> std1.getUniversity(), equalTo("NMU")).value(std1 -> std1.getGpa(), equalTo(9.8))
				.value(std1 -> std1.getResult(), equalTo("Pass"));
	}

/*	@Test
	public void testGetStudentByIdNotFoundFound() {
		Mono<Student> studentMono = Mono.just(std);

		when(stdService.getStudentById(1)).thenReturn(studentMono);

		webTestClient.get().uri("/student/get/2").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isNotFound().expectBody(Student.class).value(std1 -> std1.getStdName(), equalTo("Rakesh"))
				.value(std1 -> std1.getUniversity(), equalTo("NMU")).value(std1 -> std1.getGpa(), equalTo(9.8))
				.value(std1 -> std1.getResult(), equalTo("Pass"));
	}
*/
	@Test
	public void testDeleteStudentById() {

		Mockito.when(stdService.deleteStudentById(1)).thenReturn(Mono.just("Delete Student With Id"));

		webTestClient.delete().uri("/student/deleteStd/1").exchange().expectStatus().isOk().expectBody(String.class)
				.isEqualTo("Delete Student With Id");

	}

	@Test
	public void testGetAllStudents() {
		List<Student> stdList = Arrays.asList(new Student(1, "Rakesh", "NMU", 9.8, "Pass"),
				new Student(1, "Rakesh", "NMU", 9.8, "Pass"));
		Mockito.when(stdService.getAllStudents()).thenReturn(Flux.fromIterable(stdList));

		webTestClient.get().uri("/student/getAll/").exchange().expectStatus().isOk().expectBodyList(Student.class)
				.hasSize(stdList.size());
	}

	@Test
	public void testDeleteStudentBy_Id() {
		webTestClient.delete().uri("/student/delete/1").exchange().expectStatus().isOk().expectBody(Void.class);
	}
	@Test
	public void testUpdateRecord() {
		String university="Pune";
		Student std=new Student(null,"Rakesh",university,9.4,"Pass");
		webTestClient.put().uri("/student/update/1")
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(std),Student.class)
		.exchange().expectStatus().isOk()
		.expectBody(Student.class);
	}
	
	@Test
	public void testUpdateRecord_NotFound() {
		String university="Pune";
		Student std=new Student(null,"Rakesh",university,9.4,"Pass");
		webTestClient.put().uri("/student/update/199")
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(std),Student.class)
		.exchange().expectStatus().isNotFound();
	}

}
