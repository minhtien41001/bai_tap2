package com.example.bai_tap2.repository;

import com.example.bai_tap2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.isDeleted = false ")
    List<Student> findAllStudents();

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.isDeleted = true WHERE s.id = :id")
    int deleteStudent(long id);

    @Query("SELECT s FROM Student s WHERE s.isDeleted = false AND LOWER(s.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<Student> findByName(String fullName);
}
