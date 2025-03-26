package com.example.bai_tap2.service.impl;

import com.example.bai_tap2.dto.TeacherRequest;
import com.example.bai_tap2.entity.Student;
import com.example.bai_tap2.entity.Teacher;
import com.example.bai_tap2.repository.TeacherRepository;
import com.example.bai_tap2.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAllTeachers();
    }

    @Override
    public Teacher addTeacher(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setFullName(request.getFullName());
        teacher.setEmail(request.getEmail());
        teacher.setDateOfBirth(request.getDateOfBirth());
        teacher.setSpecialize(request.getSpecialize());
        teacher.setIsDeleted(false);
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(long id, TeacherRequest teacherRequest) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isEmpty()) {
            throw new RuntimeException("Không tìm thấy giảng viên với id = " + id);
        }

        Teacher teacher = optionalTeacher.get();
        teacher.setFullName(teacherRequest.getFullName());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        teacher.setSpecialize(teacherRequest.getSpecialize());
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isEmpty() || optionalTeacher.get().getIsDeleted()) {
            throw new RuntimeException("Không tìm thấy giảng viên hoặc giảng viên đã bị xóa");
        }

        teacherRepository.deleteTeacher(id);
    }

    @Override
    public List<Teacher> searchTeachersByName(String fullName) {
        return teacherRepository.findByName(fullName);
    }
}
