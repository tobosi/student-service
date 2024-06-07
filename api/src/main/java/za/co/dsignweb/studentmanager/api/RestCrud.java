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

public interface RestCrud<R, T> extends IRestCreate<R, T> {

    @Operation(
            summary = "Retrieve resource with id",
            responses = {
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.success.code}", description = "${apiResponse.statusCodes.success.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.badRequest.code}", description = "${apiResponse.statusCodes.badRequest.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.notFound.code}", description = "${apiResponse.statusCodes.notFound.status}")
            }
    )
    @GetMapping(value = "/{refNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<R> get(@PathVariable final String refNo);

    @Operation(
            summary = "Retrieve all resources through pagination",
            responses = {
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.success.code}", description = "${apiResponse.statusCodes.success.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.badRequest.code}", description = "${apiResponse.statusCodes.badRequest.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.notFound.code}", description = "${apiResponse.statusCodes.notFound.status}")
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<List<R>>> getAll(final @RequestParam(defaultValue = "0") int page,
                                            final @RequestParam(defaultValue = "25") int size);



    @Operation(
            summary = "Search and retrieve all resources through paginatio",
            responses = {
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.success.code}", description = "${apiResponse.statusCodes.success.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.badRequest.code}", description = "${apiResponse.statusCodes.badRequest.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.notFound.code}", description = "${apiResponse.statusCodes.notFound.status}")
            }
    )
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<List<R>>> search(@RequestBody final SearchCriteriaReq searchCriteriaReq,
                                   final @RequestParam(defaultValue = "0") int page,
                                   final @RequestParam(defaultValue = "25") int size);

    @Operation(
            summary = "Update resource with id",
            responses = {
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.success.code}", description = "${apiResponse.statusCodes.success.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.badRequest.code}", description = "${apiResponse.statusCodes.badRequest.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.notFound.code}", description = "${apiResponse.statusCodes.notFound.status}")
            }
    )
    @PutMapping(value = "/{refNo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<R> update(@PathVariable final String refNo, final @Valid  @RequestBody T request);

    @Operation(
            summary = "Delete resource with id",
            responses = {
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.success.code}", description = "${apiResponse.statusCodes.success.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.badRequest.code}", description = "${apiResponse.statusCodes.badRequest.status}"),
                    @ApiResponse(responseCode = "${apiResponse.statusCodes.notFound.code}", description = "${apiResponse.statusCodes.notFound.status}")
            }
    )
    @DeleteMapping("/{refNo}")
    ResponseEntity<Void> delete(@PathVariable final String refNo);
}
