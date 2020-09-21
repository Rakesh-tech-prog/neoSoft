package com.neosoft.webflux.reactive.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.neosoft.webflux.reactive.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
	public Mono<Student> createStudent(@RequestBody Student std);
	public Mono<Student>getStudentById(@PathVariable("id") Integer id);
	public Flux<Student> getAllStudents();
	public  Mono<ResponseEntity<Student>> updateStudentById(@RequestBody Student std, @PathVariable("id") Integer id);
	public  Mono<Void> deleteStudentBy_Id( Integer id);
	public Flux<Student> findStudentByName(String name);
	public Mono<String>deleteStudentById(Integer id);

}
