package com.teacher.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

	@Autowired
	TeacherSpringDataRepository teacherSpringDataRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Teacher>  getAllTeacher(){
		
		// List<Teacher> t = s.stream().filter(c -> c.getId()> 2).collect(Collectors.toList());

		return teacherSpringDataRepository.findAll();
		
	}
public List<Student> findByTeacherId(int id){
		
		List<Student> s= studentRepository.findByTeacherId(id);
		
		 List<Student> student = s.stream().filter(c -> c.getId()> 2 && c.getName().startsWith("A"))
				 .collect(Collectors.toList());
		
//		 Integer[] empIds = { 1, 2, 3 };
//		 
//	 List<List<Student>> student = Stream.of(empIds).map(studentRepository::findByTeacherId).collect(Collectors.toList());
//		
		 
		 return student ;
		
	}
	
	public Teacher getTeacherByName(String name) throws ResourceNotFoundException {
		
	Optional<Teacher> t=	teacherSpringDataRepository.findByName(name);
		
		return t.stream().filter(c -> c.getName().equals(name))
				.findAny().orElseThrow(()->new ResourceNotFoundException("Name is not found"));
		
	}

	
	public  Teacher addNewTeacher(Teacher teacher) {
		
		return teacherSpringDataRepository.save(teacher);  
	}


	
}
