package com.manytomany.demom_m.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.manytomany.demom_m.Entity.Address;
import com.manytomany.demom_m.Entity.Student;
import com.manytomany.demom_m.Error.StudentNotFoundException;
import com.manytomany.demom_m.Repository.AddressRepo;
import com.manytomany.demom_m.Repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private AddressRepo addressRepo;
	
	

	public Student saveStudent(Student student) {

//		Set<Address> addressDb =(Set<Address>) addressRepo.saveAll(student.getAddresses());
//		student.setAddresses( addressDb);
//		return studentRepo.save(student);

		Set<Address> savedAddresses = new HashSet<>();
		for (Address address : student.getAddresses()) {
			savedAddresses.add(addressRepo.save(address));

		}
		student.setAddresses(savedAddresses);

		return studentRepo.save(student);
	}

	// get student count
	public long getStudentCount() {
		return studentRepo.count();
	}

	// check the student is exists or not
	public boolean checkIfStudentExists(Long studentId) {
		return studentRepo.existsById(studentId);

	}

	// find all students
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	// get multiple students by Ids
	public Iterable<Student> getStudentsByIds(List<Long> studentsIds) {
		return studentRepo.findAllById(studentsIds);
	}

	// get student by id
	public Student getStudentById(Long id) throws StudentNotFoundException {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepo.findById(id);

		if (!student.isPresent()) {
			throw new StudentNotFoundException("student Not Available");
		}
		return student.get();
	}

	// delete student by id
	public void deleteStudentById(Long studentId) {
		studentRepo.deleteById(studentId);

	}

	// delete all students from database
	public void deleteAllStudents() {
		studentRepo.deleteAll();
	}

	// pagination methods

	public Page<Student> getAllStudentsWithPagination(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return studentRepo.findAll(pageable);
	}

	public Page<Student> getAllStudentsSortedById(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending()); // sort the data in descending order
		return studentRepo.findAll(pageable);
	}

	public List<Student> getByAgeAndName(Long age, String name) {
		
		
//If we use this statement by instead of if else reduce the time of execution		
		return studentRepo.findByAgeAndNameOrAgeOrName(age , name, age,name);
		
//		if (name != null)
//			return studentRepo.findByAgeAndName(age, name);
//		else {
//			return studentRepo.getByAge(age);
//		}
	}

	public List<Student> getByAge(Optional<Long> age) {
		// TODO Auto-generated method stub
		return studentRepo.findByAge(age);
	}

	public List<Student> getByAge(Long age) {

		return studentRepo.getByAge(age);
	}

	
	//Find by subject name
	
	public List<Student> findStudentsBySubjectName(String subjectname) {
        return studentRepo.findBySubjectsSubjectname(subjectname);
    }

}
