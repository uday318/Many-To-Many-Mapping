package com.manytomany.demom_m.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manytomany.demom_m.Entity.Subject;
import com.manytomany.demom_m.Repository.SubjectRepo;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectRepo subjectRepo;

	public Subject saveSubject(Subject subject) {
		// TODO Auto-generated method stub
		return subjectRepo.save(subject);
	}

	
}
