package za.co.dsignweb.studentmanager.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import za.co.dsignweb.studentmanager.db.entity.Score;
import za.co.dsignweb.studentmanager.db.entity.Student;
import za.co.dsignweb.studentmanager.db.repository.StudentRepository;
import za.co.dsignweb.studentmanager.model.ValidationMessage;
import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;
import za.co.dsignweb.studentmanager.model.api.request.SearchCriteriaReq;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.model.dto.StudentDto;
import za.co.dsignweb.studentmanager.service.contract.StudentService;
import za.co.dsignweb.studentmanager.service.contract.validation.StudentValidation;
import za.co.dsignweb.studentmanager.service.dao.ScoreDAO;
import za.co.dsignweb.studentmanager.service.impl.base.BaseCrudService;
import za.co.dsignweb.studentmanager.service.impl.search.SearchCriteriaSpecification;
import za.co.dsignweb.studentmanager.service.util.StringUtility;
import za.co.dsignweb.studentmanager.service.util.UniqueIdGenerator;

@Transactional
@Service
public class StudentServiceImpl extends BaseCrudService<StudentReq, StudentDto, Student, StudentRepository> implements StudentService {

    private final StudentRepository studentRepository;
    private final ScoreDAO scoreDAO;
    private final StringUtility stringUtility;
    private static final String PATTERN = "dd/MM/yyyy";

    public StudentServiceImpl(final StudentRepository studentRepository,
                              final ScoreDAO scoreDAO,
                              final StringUtility stringUtility) {
        super(studentRepository);
        this.studentRepository = studentRepository;
        this.scoreDAO = scoreDAO;
        this.stringUtility = stringUtility;
    }

    @Override
    public Page<StudentDto> search(final SearchCriteriaReq request, final Pageable pageable) {
        return studentRepository.findAll(
                Specification.where(
                        SearchCriteriaSpecification.hasParam(request.searchCriteria(), request.value())
                ),
                pageable
        ).map(Student::toDTO);
    }

    @Override
    public StudentDto create(final StudentReq request) {
        final ValidationMessage validationError = validateRequest(request);
        if (isSuccessfulValidation(validateRequest(request))) {
            final Student student = new Student();

            final String fName = UniqueIdGenerator.getCharacter.apply(request.firstName(), 0);
            final String lName = UniqueIdGenerator.getCharacter.apply(request.lastName(), 0);
            final String idNo = UniqueIdGenerator.randomDigit.apply(6);

            student.setRefNo(String.format("%s%s%s", fName, idNo, lName));

            build(student, request);

            studentRepository.save(student);

            final ScoreReq scoreReq = new ScoreReq(request.score(), student.getRefNo());
            final Score score = scoreDAO.save(ScoreServiceImpl.build(scoreReq, student));
            student.getScores().add(score);

            return student.toDTO();
        }

        throw ValidationMessage.getException(validationError);
    }

    @Override
    public StudentDto update(final String refNo, final StudentReq request) {
        final ValidationMessage validationError = validateRequest(request);
        if (isSuccessfulValidation(validateRequest(request))) {
            final Student student = findEntityByID(refNo);
            build(student, request);
            return convert(studentRepository.save(student));
        }

        throw ValidationMessage.getException(validationError);
    }

    private void build(final Student student, final StudentReq studentReq) {
        student.setFirstName(stringUtility.capitalize(studentReq.firstName()));
        student.setLastName(stringUtility.capitalize(studentReq.lastName()));
        student.setDob(studentReq.dob());
        student.setEmail(studentReq.email());
        student.setCellphoneNo(studentReq.cellphoneNo());
    }

    private ValidationMessage validateRequest(final StudentReq request) {
        return StudentValidation.validatePhoneNumber().apply(request);
    }

    private boolean isSuccessfulValidation(final ValidationMessage message) {
        return ValidationMessage.SUCCESS.equals(message);
    }
}
