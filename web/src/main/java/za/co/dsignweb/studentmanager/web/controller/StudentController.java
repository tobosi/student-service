package za.co.dsignweb.studentmanager.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.dsignweb.studentmanager.api.RestConverter;
import za.co.dsignweb.studentmanager.api.RestCrud;
import za.co.dsignweb.studentmanager.model.api.request.SearchCriteriaReq;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.model.api.response.StudentRes;
import za.co.dsignweb.studentmanager.model.api.response.Response;
import za.co.dsignweb.studentmanager.model.dto.StudentDto;
import za.co.dsignweb.studentmanager.service.contract.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController extends BaseController implements RestCrud<StudentRes, StudentReq>, RestConverter<StudentRes, StudentDto> {

    private final StudentService service;
    private final ObjectMapper mapper;
    private final static Class<StudentRes> responseType = StudentRes.class;

    public StudentController(final StudentService service,
                             final ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<Response<List<StudentRes>>> search(final SearchCriteriaReq searchCriteriaReq, final int page, final int size) {
        final Page<StudentDto> details = service.search(searchCriteriaReq, getPageable(page, size));
        return ResponseEntity.ok(getResponse(details.getTotalElements(), details.getTotalPages(), details));
    }

    @Override
    public ResponseEntity<Response<List<StudentRes>>> getAll(final int page, final int size) {
        final Page<StudentDto> details = service.findAll(getPageable(page, size));
        return ResponseEntity.ok(getResponse(details.getTotalElements(), details.getTotalPages(), details));
    }


    @Override
    public ResponseEntity<StudentRes> get(final String refNo) {
        return convert(mapper, service.find(refNo), StudentRes.class);
    }

    @Override
    public ResponseEntity<StudentRes> create(final StudentReq request) {
        return convert(mapper, service.create(request), StudentRes.class);
    }

    @Override
    public ResponseEntity<StudentRes> update(final String refNo, final StudentReq request) {
        return convert(mapper, service.update(refNo, request), StudentRes.class);
    }

    @Override
    public ResponseEntity<Void> delete(final String refNo) {
        service.delete(refNo);
        return ResponseEntity.noContent().build();
    }

    private Response<List<StudentRes>> getResponse(final long length, final int totalPages, final Page<StudentDto> page) {
        return new Response<>(length, totalPages, convertToResponse(mapper, page.toList(), responseType));
    }
}
