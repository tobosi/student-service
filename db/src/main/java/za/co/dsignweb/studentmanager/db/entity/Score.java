package za.co.dsignweb.studentmanager.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import za.co.dsignweb.studentmanager.model.dto.ScoreDto;

@Entity
public class Score extends BaseEntity<ScoreDto> {

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private int score;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;

    @Min(0)
    @Max(100)
    public int getScore() {
        return score;
    }

    public Score() {
    }

    public Score(final int score, final Student student) {
        this.score = score;
        this.student = student;
    }

    public void setScore(@Min(0) @Max(100) final int score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(final Student student) {
        this.student = student;
    }

    @Override
    public ScoreDto toDTO() {
        return new ScoreDto(getRefNo(), score, student.getRefNo());
    }
}
