package za.co.dsignweb.studentmanager.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import za.co.dsignweb.studentmanager.model.dto.StudentDto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends BaseEntity<StudentDto> {

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private LocalDate dob;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String cellphoneNo;

    @OneToMany(mappedBy = "student")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Score> scores = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(final LocalDate dob) {
        this.dob = dob;
    }

    public String getCellphoneNo() {
        return cellphoneNo;
    }

    public void setCellphoneNo(final String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Set<Score> getScores() {
        return scores;
    }

    @Override
    public StudentDto toDTO() {
        return new StudentDto(
                getRefNo(),
                firstName,
                lastName,
                email,
                cellphoneNo,
                dob,
                scores.stream().max(Comparator.comparing(BaseEntity::getId)).map(Score::getScore).orElse(0),
                scores.stream().mapToDouble(score -> (double) score.getScore()).average().orElse(0.0)
        );
    }
}
