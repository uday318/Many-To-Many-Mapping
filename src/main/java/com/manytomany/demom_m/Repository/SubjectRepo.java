package com.manytomany.demom_m.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.demom_m.Entity.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
	
}
