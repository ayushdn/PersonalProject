package com.teacher.database;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class studentController {
	
	  @Autowired 
	  private TeacherService teacherService;
	  
	  @Autowired
	  private TeacherSpringDataRepository teacherSpringDataRepository;
	  
	  @Autowired
	  private StudentRepository studentRepository;
	  
		@GetMapping("/listTeacher")
	     public ResponseEntity<List<Teacher>> getAllTeacher(){
	    	 List<Teacher> teacher= teacherService.getAllTeacher();

			try {
				
	    	 if (teacher.isEmpty()) {
	    		
	         return ResponseEntity.noContent().build();
	    	 }
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return  ResponseEntity.ok().body(teacher);

	     }
	     
	     @GetMapping("teacher/{id}/student")
	     public List<Student> getStudentByTeacher(@PathVariable(value="id")int id){
	    	
	    			
	    			 
	         return teacherService.findByTeacherId(id);
	     }
	     
	     
	 
	    @PostMapping("/addTeacher")
	    public  Teacher addNewTeacher(@RequestBody Teacher teacher) {
			
			return teacherService.addNewTeacher(teacher);  
		}
	     
	     @GetMapping("/listTeacher/")
	     public ResponseEntity<Teacher> getTeacherById(@Valid @PathVariable(value="name")String name) throws ResourceNotFoundException	     
	     {
	    	 
	    	 Teacher teacher= teacherService.getTeacherByName(name);
	         return ResponseEntity.ok().body(teacher);
	     }
	     
	     @PostMapping("teacher/{id}/student")
	     public Student addNewStudent(@PathVariable(value="id") int id, @Valid @RequestBody Student student
	    		 ) throws ResourceNotFoundException {
	         return teacherSpringDataRepository.findById(id).map(teacher ->{
	             student.setTeacher(teacher);
	             return studentRepository.save(student);
	         }).orElseThrow(() -> new ResourceNotFoundException("teacher not found"));
	     }
}