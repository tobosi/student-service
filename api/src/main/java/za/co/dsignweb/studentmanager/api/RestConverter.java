package za.co.dsignweb.studentmanager.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface RestConverter<R, T> {

    default ResponseEntity<List<R>> convert(final ObjectMapper objectMapper, final List<T> dtos, final Class<R> resClass) {
        return ResponseEntity.ok(convertToResponse(objectMapper, dtos, resClass));
    }

    default List<R> convertToResponse(final ObjectMapper objectMapper, final List<T> dtos, final Class<R> resClass) {

        return dtos.stream()
                        .map(dto -> map(objectMapper, dto, resClass))
                        .collect(Collectors.toList()
        );
    }

    default ResponseEntity<R> convert(final ObjectMapper objectMapper, final T dto, final Class<R> resClass) {
        return ResponseEntity.ok(map(objectMapper, dto, resClass));
    }

    private R map(final ObjectMapper objectMapper, final T dto, final Class<R> resClass) {
        return objectMapper.convertValue(dto, resClass);
    }
}
