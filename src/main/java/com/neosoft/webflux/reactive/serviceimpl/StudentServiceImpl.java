package com.neosoft.webflux.reactive.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neosoft.webflux.reactive.model.Student;
import com.neosoft.webflux.reactive.repository.StudentRepo;
import com.neosoft.webflux.reactive.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo stdRepo;

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Student> createStudent(Student std) {
		return stdRepo.save(std);
				
				
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	public Mono<Student> getStudentById(Integer id) {
		return stdRepo.findById(id);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	public Flux<Student> getAllStudents() {
		return stdRepo.findAll();
	}
/*
 * 
 * Updating the Student Record With Id
 * 
 * 
 */
	@Override
	public Mono<ResponseEntity<Student>> updateStudentById(Student std, Integer id) {
		return stdRepo.findById(id).flatMap(cur -> {
			cur.setStdName(std.getStdName());
			cur.setUniversity(std.getUniversity());
			cur.setGpa(std.getGpa());
			cur.setResult(std.getResult());
			return stdRepo.save(cur);
		}).map(student -> new ResponseEntity<Student>(student, HttpStatus.OK))
			.defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));
	}

	
	@Override
	public Flux<Student> findStudentByName(String name) {
		return stdRepo.findStudentByName(name);
	}

	
	  @Override
	  @ResponseStatus(HttpStatus.OK)
	  public Mono<Void> deleteStudentBy_Id(Integer id) {
	  	  return stdRepo.deleteById(id); 
	  	  }
	 

	public Mono<String>deleteStudentById(Integer id){
		return stdRepo.DeleteStudentById(id);
		
	}

}