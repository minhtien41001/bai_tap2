package com.example.bai_tap2.repository;

import com.example.bai_tap2.entity.Student;
import com.example.bai_tap2.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE t.isDeleted = false ")
    List<Teacher> findAllTeachers();

    @Transactional
    @Modifying
    @Query("UPDATE Teacher t SET t.isDeleted = true WHERE t.id = :id")
    int deleteTeacher(long id);

    @Query("SELECT t FROM Teacher t WHERE t.isDeleted = false AND LOWER(t.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<Teacher> findByName(String fullName);
}
