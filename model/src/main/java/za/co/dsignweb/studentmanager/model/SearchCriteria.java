package za.co.dsignweb.studentmanager.model;

public enum SearchCriteria {
    STUDENT_NUMBER("refNo"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    EMAIL("email");

    private final String parameter;

    SearchCriteria(final String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
