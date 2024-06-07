package za.co.dsignweb.studentmanager.model.dto;

import java.time.LocalDate;

public record StudentDto(
        String studentNo,
        String firstName,
        String lastName,
        String email,
        String cellphoneNo,
        LocalDate dob,
        int score,
        double average
) { }
