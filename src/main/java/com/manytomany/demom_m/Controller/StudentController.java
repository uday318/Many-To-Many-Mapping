package com.manytomany.demom_m.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manytomany.demom_m.Entity.Student;
import com.manytomany.demom_m.Error.StudentNotFoundException;
import com.manytomany.demom_m.Service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@PostMapping("/saveStudent")
	public Student saveStudent(@RequestBody Student student) {
	
		return studentService.saveStudent(student);
	}
	
	@GetMapping("/getStudentCount")
	public long getStudentCount() {
		return studentService.getStudentCount();
		
	}
	
	 @GetMapping("/getAllStudents")
	    public List<Student> getAllStudents() {
	        return studentService.getAllStudents();
	 }
	
	@GetMapping("/checkStudent/{studentId}")
	public boolean checkIfStudentExists(@PathVariable Long studentId) {
		return studentService.checkIfStudentExists(studentId);
	}
	
	@GetMapping("/getStudentsByIds/{ids}")
    public Iterable<Student> getStudentsByIds(@PathVariable List<Long> ids) {
        return studentService.getStudentsByIds(ids);
    }
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) throws StudentNotFoundException  {
		return studentService.getStudentById(id);
	}
	
	 
	@DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
		studentService.deleteStudentById(studentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	 @DeleteMapping("/deleteAll")
	    public ResponseEntity<Void> deleteAllStudents() {
		 studentService.deleteAllStudents();
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
	 
	 
	 
	 //pagination   
	 
	 @GetMapping("/all")
	    public ResponseEntity<Page<Student>> getAllStudents(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "50") int size) {

	        Page<Student> students = studentService.getAllStudentsWithPagination(page, size);

	        return ResponseEntity.ok(students);
	    }
	  
	  
	  @GetMapping("/allById")
	    public ResponseEntity<Page<Student>> getAllStudentsById  (
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size) {

	        Page<Student> students = studentService.getAllStudentsSortedById(page, size);

	        return ResponseEntity.ok(students);
	    }
	  
	  
	  
	 //get by multiple parameters
	  
	  @GetMapping("/getByAgeAndName/{age}")
		public List<Student> getByAgeAndName(@PathVariable Long age,  @RequestParam(required = false) String name) {
		  
		  return studentService.getByAgeAndName(age, name);
		}
	  
	  
	  
	  
	  @GetMapping("/getByAge")
		public List<Student> getByAge(@RequestParam(required = false)Optional<Long> age) {
			return studentService.getByAge(age);
		}
	  
	  
	  //get students by subject
	  
	  
	  @GetMapping("/bySubject")
	    public ResponseEntity<List<Student>> getStudentsBySubjectName(@RequestParam String subjectname) {
	        List<Student> students = studentService.findStudentsBySubjectName(subjectname);
	        return new ResponseEntity<>(students, HttpStatus.OK);
	    }
	  

	 
}
