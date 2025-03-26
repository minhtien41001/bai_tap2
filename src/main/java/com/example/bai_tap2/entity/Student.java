package com.example.bai_tap2.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
    private String className;

    private double points;

    public Student(long id, String fullName, String email, LocalDate dateOfBirth, Boolean isDeleted, String className, double points) {
        super(id, fullName, email, dateOfBirth, isDeleted);
        this.className = className;
        this.points = points;
    }
}
