package com.teacher.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherSpringDataRepository extends JpaRepository<Teacher,Integer>{

	Optional<Teacher> findByName(String name);




}
