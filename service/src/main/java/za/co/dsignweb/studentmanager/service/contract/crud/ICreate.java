package za.co.dsignweb.studentmanager.service.contract.crud;

import jakarta.validation.Valid;

public interface ICreate<R, T> {
    T create(@Valid final R request);
}
