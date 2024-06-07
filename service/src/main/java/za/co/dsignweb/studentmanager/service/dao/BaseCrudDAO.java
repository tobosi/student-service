package za.co.dsignweb.studentmanager.service.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.co.dsignweb.studentmanager.db.BaseRepository;
import za.co.dsignweb.studentmanager.db.entity.BaseEntity;
import za.co.dsignweb.studentmanager.model.exception.NotFoundException;

public abstract class BaseCrudDAO<E extends BaseEntity<?>, D extends BaseRepository<E>> {

    private final D repository;

    protected BaseCrudDAO(final D repository) {
        this.repository = repository;
    }

    public final E find(final String refNo) {
        return findEntityByID(refNo);
    }

    public final Page<E> findAll(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    public final void delete(final String refNo) {
        repository.delete(findEntityByID(refNo));
    }

    protected final E save(final E entity) {
        return repository.save(entity);
    }

    public final E findEntityByID(final String refNo) {
        return repository.findByRefNo(refNo).orElseThrow(() -> new NotFoundException(String.format("Could not find resource id: %d", refNo)));
    }
}
