package com.example.bai_tap2.service;

import com.example.bai_tap2.dto.StudentRequest;
import com.example.bai_tap2.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    Student addStudent(StudentRequest request);

    Student updateStudent(long id, StudentRequest studentRequest);

    void deleteStudent(long id);

    List<Student> searchStudentsByName(String fullName);
}
