package com.teacher.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
	List<Student> findByTeacherId(int did);
	Optional<Student> findByIdAndTeacherId(int id,int did);
	

}
