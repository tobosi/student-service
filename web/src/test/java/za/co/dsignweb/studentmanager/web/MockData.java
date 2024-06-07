package za.co.dsignweb.studentmanager.web;

import za.co.dsignweb.studentmanager.db.entity.Score;
import za.co.dsignweb.studentmanager.db.entity.Student;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MockData {
    public static final String PERSISTED_STUDENT_ID = "J77042G";
    public static final String FIRST_NAME_REQ = "jack";
    public static final String LAST_NAME_REQ = "mack";
    public static final String EMAIL_REQ = "test@gmail.com";
    public static final String CELLPHONE_REQ = "+27742546875";
    public static final String CELLPHONE_REQ_INVALID = "0742546875";
    public static final int SCORE_REQ = 60;

    public static Student persistStudentWithScores(final List<Integer> scores, final Student student) {
        int i = 1;
        for(int score : scores) {
            student.getScores().add(persistedScoreEntity(score, i, student));
        }
        return student;
    }

    public static Student persistedStudentEntity() {
        final Student student = new Student();
        student.setRefNo("J77042G");
        student.setFirstName("Jack");
        student.setLastName("Gym");
        student.setEmail("test6@gmail.com");
        student.setCellphoneNo("+27824874259");
        student.setDob(LocalDate.now());

        return student;
    }

    public static StudentReq getStudentRequest(final String number) {
        return new StudentReq(
                FIRST_NAME_REQ,
                LAST_NAME_REQ,
                EMAIL_REQ,
                number,
                LocalDate.now(),
                SCORE_REQ
        );
    }

    public static Score persistedScoreEntity(final int score, final int index, final Student student) {
        final Score scoreE = new Score();
        scoreE.setId((long) index);
        scoreE.setRefNo(UUID.randomUUID().toString());
        scoreE.setScore(score);
        scoreE.setStudent(student);

        return scoreE;
    }
}
