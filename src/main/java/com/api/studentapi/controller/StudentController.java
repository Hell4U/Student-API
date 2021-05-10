package com.api.studentapi.controller;

import com.api.studentapi.domain.Student;
import com.api.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping
    public Student hello(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable(name = "id") Long id){
        return studentRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Student> getALl(){
        return studentRepository.findAll();
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable(name="id") Long id, @RequestBody Student student){
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        studentRepository.deleteById(id);
    }

    @GetMapping("/search-by-First-Name/{name}")
    public List<Student> nameContaining(@PathVariable(name = "name") String name){
        return studentRepository.nameContains(name);
    }

    @GetMapping("/search/{name}")
    public List<Student> search(@PathVariable(name ="name") String name){
        return studentRepository.search(name);
    }

    @GetMapping("/get-dob/{dob}")
    public List<Student> findDOB(@PathVariable(name = "dob") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate localDate){
        return studentRepository.findStudentByDob(localDate);
    }

    @GetMapping("/get-age-by-id/{id}")
    public int findAge(@PathVariable(name = "id") Long id){
        Optional<Student> student=studentRepository.findById(id);
        return student.get().getAge();
    }

    @GetMapping("/get-Student-age-lessThan/{age}")
    public List<Student> ageLessThan(@PathVariable(name = "age") Integer age){
        List<Student> student=studentRepository.findAll();
        List<Student> answer=new ArrayList<Student>();

        for ( Student st:
             student) {
            int x=st.getAge();
            if(x<age){
                answer.add(st);
            }
        }

        return answer;
    }

    @GetMapping("/search-by-university/{university}")
    public List<Student>  findByUniversity(@PathVariable(name = "university") String university){
        return studentRepository.findStudentByUniversityContaining(university);
    }



}
