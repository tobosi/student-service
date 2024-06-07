package za.co.dsignweb.studentmanager.service.contract.crud;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<R, T> extends ICreate<R, T> {

    T find(final String refNo);

    Page<T> findAll(final Pageable pageable);

    T update(final String refNo, @Valid final R request);

    void delete(final String refNo);
}
