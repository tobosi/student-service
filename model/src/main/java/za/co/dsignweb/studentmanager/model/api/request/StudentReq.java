package za.co.dsignweb.studentmanager.model.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentReq(
        @NotNull @NotBlank String firstName,
        @NotNull @NotBlank String lastName,
        @NotNull @NotBlank @Email String email,
        @NotNull @NotBlank String cellphoneNo,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy") LocalDate dob,
        @Min(0) @Max(100) int score
) {}
