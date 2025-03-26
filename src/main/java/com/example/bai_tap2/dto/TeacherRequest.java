package com.example.bai_tap2.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherRequest {
    private String fullName;

    private String email;

    private LocalDate dateOfBirth;

    private String specialize;
}
