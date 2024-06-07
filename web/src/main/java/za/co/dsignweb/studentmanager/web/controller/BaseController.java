package za.co.dsignweb.studentmanager.web.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class BaseController {
    final Pageable getPageable(final int page, final int size) {
        return PageRequest.of(page, size);
    }
}
