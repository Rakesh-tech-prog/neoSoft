package com.neosoft.webflux.reactive.repository;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neosoft.webflux.reactive.model.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepo extends R2dbcRepository<Student, Integer> {
	@Query("Select * from student s where s.stdName =:name ")
	public Flux<Student> findStudentByName(@Param("name")String name);
	@Query("delete from student s where s.stdId =:stdId ")
	public Mono<String> DeleteStudentById(@Param("stdId")Integer stdId);
}
