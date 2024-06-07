package za.co.dsignweb.studentmanager.model.api.response;

import java.io.Serializable;
import java.util.Collection;

public class Response<T extends Collection> implements Serializable {

    private final long length;
    private final int totalPages;
    private final T content;

    public Response(final long length, final int totalPages, final T content) {
        this.length = length;
        this.totalPages = totalPages;
        this.content = content;
    }

    public long getLength() {
        return length;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public T getContent() {
        return content;
    }
}
