package za.co.dsignweb.studentmanager.db.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import za.co.dsignweb.studentmanager.db.BaseRepository;
import za.co.dsignweb.studentmanager.db.entity.Student;

@Repository
public interface StudentRepository extends BaseRepository<Student>, JpaSpecificationExecutor<Student> {
}
