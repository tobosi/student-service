package za.co.dsignweb.studentmanager.service.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.dsignweb.studentmanager.model.api.request.SearchCriteriaReq;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.model.dto.StudentDto;
import za.co.dsignweb.studentmanager.service.contract.crud.CrudService;

public interface StudentService extends CrudService<StudentReq, StudentDto> {
    Page<StudentDto> search(final SearchCriteriaReq request, final Pageable pageable);
}
