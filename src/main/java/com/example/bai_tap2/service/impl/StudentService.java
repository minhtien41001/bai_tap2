package com.example.bai_tap2.service.impl;

import com.example.bai_tap2.dto.StudentRequest;
import com.example.bai_tap2.entity.Student;
import com.example.bai_tap2.repository.StudentRepository;
import com.example.bai_tap2.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public Student addStudent(StudentRequest request) {
        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setClassName(request.getClassName());
        student.setPoints(request.getPoints());
        student.setIsDeleted(false);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(long id, StudentRequest studentRequest) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sinh viên với id = " + id);
        }

        Student student = optionalStudent.get();
        student.setFullName(studentRequest.getFullName());
        student.setEmail(studentRequest.getEmail());
        student.setDateOfBirth(studentRequest.getDateOfBirth());
        student.setClassName(studentRequest.getClassName());
        student.setPoints(studentRequest.getPoints());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isEmpty() || optionalStudent.get().getIsDeleted()) {
            throw new RuntimeException("Không tìm thấy sinh viên hoặc sinh viên đã bị xóa");
        }

        studentRepository.deleteStudent(id);
    }

    @Override
    public List<Student> searchStudentsByName(String fullName) {
        return studentRepository.findByName(fullName);
    }
}
