package com.example.bai_tap2.service;

import com.example.bai_tap2.dto.StudentRequest;
import com.example.bai_tap2.dto.TeacherRequest;
import com.example.bai_tap2.entity.Student;
import com.example.bai_tap2.entity.Teacher;

import java.util.List;

public interface ITeacherService {
    List<Teacher> findAll();

    Teacher addTeacher(TeacherRequest request);

    Teacher updateTeacher (long id, TeacherRequest teacherRequest);

    void deleteTeacher(long id);

    List<Teacher> searchTeachersByName(String fullName);
}
