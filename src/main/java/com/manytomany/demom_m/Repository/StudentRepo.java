package com.manytomany.demom_m.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.demom_m.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

	Page<Student> findAll(Pageable pageable);


	public List<Student> findByAgeAndName(Long age,String name);


	public List<Student> findByAge(Optional<Long> age);


	public List<Student> getByAge(Long age);

	public List<Student> findByAgeAndNameOrAgeOrName(Long age, String name, Long age2, String name2);
	

		List<Student> findBySubjectsSubjectname(String subjectname);
	

}