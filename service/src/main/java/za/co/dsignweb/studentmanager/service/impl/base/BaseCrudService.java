package za.co.dsignweb.studentmanager.service.impl.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.dsignweb.studentmanager.db.BaseRepository;
import za.co.dsignweb.studentmanager.db.entity.BaseEntity;
import za.co.dsignweb.studentmanager.model.exception.NotFoundException;
import za.co.dsignweb.studentmanager.service.contract.crud.CrudService;

public abstract class BaseCrudService<R, T, E extends BaseEntity<T>, D extends BaseRepository<E>> implements CrudService<R, T> {

    private final D repository;

    protected BaseCrudService(final D repository) {
        this.repository = repository;
    }

    public T find(final String refNo) {
        return convert(findEntityByID(refNo));
    }

    public Page<T> findAll(final Pageable pageable) {
        return repository.findAll(pageable)
                .map(E::toDTO);
    }

    public void delete(final String refNo) {
        repository.delete(findEntityByID(refNo));
    }

    protected final T convert(final E entity) {
        return entity.toDTO();
    }

    public E findEntityByID(final String refNo) {
        return repository.findByRefNo(refNo).orElseThrow(() -> new NotFoundException(String.format("Could not find resource id: %d", refNo)));
    }

    public abstract T create(final R request);

    public abstract T update(final String refNo, final R request);
}
