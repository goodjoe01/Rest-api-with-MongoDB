package com.example.demo;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
//import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public Student findStudentById(String id) throws Exception{
        Optional<Student> student=studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }
        else{
            throw new Exception();
        }
    }
    public Student updateStudentById(String id, Student student) throws Exception{
        Optional<Student> existingStudent=studentRepository.findById(id);
        if(existingStudent.isPresent()){
            Student newStudent=existingStudent.get();
            newStudent.setFirstName(student.getFirstName());
            newStudent.setLastName(student.getLastName());
            newStudent.setAddress(student.getAddress());
            newStudent.setCreated(student.getCreated());
            newStudent.setEmail(student.getEmail());
            newStudent.setFavoriteSubjects(student.getFavoriteSubjects());
            newStudent.setGender(student.getGender());
            newStudent.setTotalSpentInBooks(student.getTotalSpentInBooks());
            return createStudent(newStudent);
        }
        else{
            throw new Exception();
        }
    }

    public void deleteStudentById(String id){
        studentRepository.deleteById(id);
    }

    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }

    public Student findStudentByEmail(String email) throws Exception{
        Optional<Student> studentFound = studentRepository.findStudentByEmail(email);
        return studentFound.get();
    }
}
