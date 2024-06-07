package za.co.dsignweb.studentmanager.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseRepository<E> extends JpaRepository<E, Long> {
    Optional<E> findByRefNo(final String no);
}
