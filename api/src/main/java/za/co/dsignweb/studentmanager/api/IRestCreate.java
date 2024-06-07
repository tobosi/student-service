package za.co.dsignweb.studentmanager.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.dsignweb.studentmanager.model.api.request.SearchCriteriaReq;
import za.co.dsignweb.studentmanager.model.api.response.Response;

import java.util.List;

public interface IRestCreate<R, T> {
    @Operation(
            summary = "Create resource",
            responses = {
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.success.code}", description = "${apiResponse.statusCodes.success.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.badRequest.code}", description = "${apiResponse.statusCodes.badRequest.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.notFound.code}", description = "${apiResponse.statusCodes.notFound.status}")
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<R> create(final @Valid @RequestBody T request);
}
