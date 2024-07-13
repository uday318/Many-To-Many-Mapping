package com.manytomany.demom_m.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manytomany.demom_m.Entity.Subject;
import com.manytomany.demom_m.Service.SubjectService;

@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/addsubject")
	public Subject saveSubject(@RequestBody Subject subject)  
	{
		return subjectService.saveSubject(subject);
		
	}
}
