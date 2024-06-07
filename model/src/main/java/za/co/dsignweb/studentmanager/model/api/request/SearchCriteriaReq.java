package za.co.dsignweb.studentmanager.model.api.request;

import jakarta.validation.constraints.NotNull;
import za.co.dsignweb.studentmanager.model.SearchCriteria;

public record SearchCriteriaReq(@NotNull SearchCriteria searchCriteria, String value) { }
