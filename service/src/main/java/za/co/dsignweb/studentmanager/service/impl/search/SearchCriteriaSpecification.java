package za.co.dsignweb.studentmanager.service.impl.search;

import org.springframework.data.jpa.domain.Specification;
import za.co.dsignweb.studentmanager.db.entity.Student;
import za.co.dsignweb.studentmanager.model.SearchCriteria;

public class SearchCriteriaSpecification {

    // TODO: this class could be extended to be more generic and build the criteria dynamically
    public static Specification<Student> hasParam(final SearchCriteria param, final String value) {
        return (root, query, cb) -> cb.like(cb.lower(root.get(param.getParameter())), "%" + value.toLowerCase() + "%");
    }
}
