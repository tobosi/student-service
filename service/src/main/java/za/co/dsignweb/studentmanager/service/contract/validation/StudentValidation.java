package za.co.dsignweb.studentmanager.service.contract.validation;

import za.co.dsignweb.studentmanager.model.ValidationMessage;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.service.util.PhoneValidationUtil;

import java.util.function.Function;

import static za.co.dsignweb.studentmanager.model.ValidationMessage.INVALID_PHONE_NUMBER;
import static za.co.dsignweb.studentmanager.model.ValidationMessage.SUCCESS;

public interface StudentValidation extends Function<StudentReq, ValidationMessage> {

    static StudentValidation validatePhoneNumber() {
            return request -> PhoneValidationUtil.INSTANCE.isValid(request.cellphoneNo()) ? SUCCESS: INVALID_PHONE_NUMBER;
    }

    default StudentValidation and(final StudentValidation other) {
        return student -> {
            final ValidationMessage validation = this.apply(student);
            return (validation == SUCCESS) ? other.apply(student): validation;
        };
    };
}
