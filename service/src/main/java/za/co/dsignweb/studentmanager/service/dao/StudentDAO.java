package za.co.dsignweb.studentmanager.service.dao;

import org.springframework.stereotype.Component;
import za.co.dsignweb.studentmanager.db.entity.Student;
import za.co.dsignweb.studentmanager.db.repository.StudentRepository;

@Component
public class StudentDAO extends BaseCrudDAO<Student, StudentRepository> {
    public StudentDAO(final StudentRepository repository, final StudentRepository studentRepository) {
        super(repository);
    }
}
