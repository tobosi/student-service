package za.co.dsignweb.studentmanager.web.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import za.co.dsignweb.studentmanager.db.entity.Score;
import za.co.dsignweb.studentmanager.db.entity.Student;
import za.co.dsignweb.studentmanager.db.repository.ScoreRepository;
import za.co.dsignweb.studentmanager.db.repository.StudentRepository;
import za.co.dsignweb.studentmanager.model.SearchCriteria;
import za.co.dsignweb.studentmanager.model.api.request.SearchCriteriaReq;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.model.dto.StudentDto;
import za.co.dsignweb.studentmanager.model.exception.InvalidPhoneNumberException;
import za.co.dsignweb.studentmanager.service.config.IgnoreCaseConfig;
import za.co.dsignweb.studentmanager.service.dao.ScoreDAO;
import za.co.dsignweb.studentmanager.service.impl.StudentServiceImpl;
import za.co.dsignweb.studentmanager.service.util.StringUtility;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static za.co.dsignweb.studentmanager.web.MockData.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreDAO scoreDAO;

    @InjectMocks
    private StudentServiceImpl service;

    @BeforeEach
    public void setUp() {
        IgnoreCaseConfig ignoreCaseConfig = new IgnoreCaseConfig();
        ignoreCaseConfig.setWordIgnoreCase(Set.of());

        final StringUtility stringUtility = new StringUtility(ignoreCaseConfig);
        service = new StudentServiceImpl(studentRepository, scoreDAO, stringUtility);
    }

    @Test
    public void testGet() {
        Mockito.when(studentRepository.findByRefNo(PERSISTED_STUDENT_ID))
                .thenReturn(Optional.of(persistedStudentEntity()));
        final StudentDto dto = service.find(PERSISTED_STUDENT_ID);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(0, dto.score());
        Assertions.assertEquals(0.0, dto.average());
    }

    @Test
    public void testGetAll() {
        Mockito.when(studentRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(
                        new PageImpl<>(Collections.singletonList(persistStudentWithScores(IntStream.of(10, 20).boxed().toList(), persistedStudentEntity())))
                );

        final Page<StudentDto> page = service.findAll(PageRequest.of(0, 25));

        Assertions.assertNotNull(page);
        Assertions.assertEquals(1, page.getTotalElements());
        Assertions.assertEquals(1, page.toList().size());
    }

    @Test
    public void testSearch() {
        Mockito.when(studentRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(
                        new PageImpl<>(Collections.singletonList(persistStudentWithScores(IntStream.of(10, 20).boxed().toList(), persistedStudentEntity())))
                );

        final SearchCriteriaReq request = new SearchCriteriaReq(SearchCriteria.FIRST_NAME, "Jack");
        final Page<StudentDto> page = service.search(request, PageRequest.of(0, 25));

        Assertions.assertNotNull(page);
        Assertions.assertEquals(1, page.getTotalElements());
        Assertions.assertEquals(1, page.toList().size());
    }

    @Test
    public void testCreate() {
        final Student student = persistedStudentEntity();
        Mockito.when(studentRepository.save(Mockito.any(Student.class)))
                .thenReturn(student);

        final StudentReq request = getStudentRequest(CELLPHONE_REQ);

        Mockito.when(scoreDAO.save(Mockito.any(Score.class)))
                .thenReturn(persistedScoreEntity(request.score(), 1, student));

        final StudentDto dto = service.create(request);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals("Jack", dto.firstName());
        Assertions.assertEquals("Mack", dto.lastName());
    }

    @Test
    public void testCreateInvalidCellphoneNumber() {
        final StudentReq request = getStudentRequest(CELLPHONE_REQ_INVALID);
        Assertions.assertThrows(InvalidPhoneNumberException.class, () -> service.create(request));
    }


    @Test
    public void testUpdate() {
        final Student student = persistedStudentEntity();
        student.setLastName("Mack");

        Mockito.when(studentRepository.findByRefNo(PERSISTED_STUDENT_ID))
                .thenReturn(Optional.of(persistedStudentEntity()));

        Mockito.when(studentRepository.save(Mockito.any(Student.class)))
                .thenReturn(student);

        final StudentReq request = getStudentRequest(CELLPHONE_REQ);

        final StudentDto dto = service.update(PERSISTED_STUDENT_ID, request);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals("Jack", dto.firstName());
        Assertions.assertEquals("Mack", dto.lastName());
    }

    @Test
    public void testDelete() {
        final Student student = persistedStudentEntity();
        Assertions.assertDoesNotThrow(() -> studentRepository.delete(student));
    }
}
