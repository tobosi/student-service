package za.co.dsignweb.studentmanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import za.co.dsignweb.studentmanager.api.RestCrud;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.model.api.response.StudentRes;

@FeignClient(name = "student", url = "${student-service-base-url}/students")
public interface StudentClient extends RestCrud<StudentRes,StudentReq> {
}
