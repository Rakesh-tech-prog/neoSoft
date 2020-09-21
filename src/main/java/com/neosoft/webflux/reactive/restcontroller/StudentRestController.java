package com.neosoft.webflux.reactive.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.webflux.reactive.model.Student;
import com.neosoft.webflux.reactive.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/create")
	private Mono<Student> createStudent(@RequestBody Student std) {
		return studentService.createStudent(std);
	}

	@GetMapping("/get/{id}")
	private Mono<Student> getStudentById(@PathVariable("id") Integer id) {

		return studentService.getStudentById(id);
	}

	@GetMapping("/getAll")
	private Flux<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@PutMapping("/update/{id}")
	private Mono<ResponseEntity<Student>> updateStudentById(@RequestBody Student std, @PathVariable("id") Integer id) {
		return studentService.updateStudentById(std, id);
	}

	@DeleteMapping("/delete/{id}")
	private Mono<Void> deleteStudentBy_Id(@PathVariable("id") Integer id) {
		return studentService.deleteStudentBy_Id(id);
	}

	@GetMapping("/getStudents/{name}")
	private Flux<Student> getStudentByName(@PathVariable("name") String name) {
		return studentService.findStudentByName(name);
	}

	@DeleteMapping("/deleteStd/{id}")
	private Mono<String> deleteStudentById(@PathVariable("id") Integer id) {
		return studentService.deleteStudentById(id);
	}

}
