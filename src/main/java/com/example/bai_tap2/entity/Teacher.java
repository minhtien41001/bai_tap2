package com.example.bai_tap2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Teacher extends Person {
    private String specialize;

    public Teacher(long id, String fullName, String email, LocalDate dateOfBirth, Boolean isDeleted, String specialize) {
        super(id, fullName, email, dateOfBirth, isDeleted);
        this.specialize = specialize;
    }
}
