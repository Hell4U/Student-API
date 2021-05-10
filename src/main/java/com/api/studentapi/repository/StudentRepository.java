package com.api.studentapi.repository;

import com.api.studentapi.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByFirstNameContaining(String name);

    @Query("FROM Student s WHERE s.firstName like %:name% ")
    List<Student> nameContains(String name);

    @Query("FROM Student s WHERE s.firstName like %:name% OR s.lastName like %:name%")
    List<Student> search(String name);

    List<Student> findStudentByDob(LocalDate localDate);

    List<Student> findStudentByUniversityContaining(String univeristy);

}
