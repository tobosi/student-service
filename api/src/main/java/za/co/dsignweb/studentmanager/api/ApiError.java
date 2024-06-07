package za.co.dsignweb.studentmanager.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;

public class ApiError {

    private static final Logger LOG = LoggerFactory.getLogger(ApiError.class);

    private final Date timestamp;
    private final int status;
    private final String debugMessage;
    private final String message;
    private final String path;

    public ApiError(final HttpStatus status, final Exception e, final String message, final WebRequest webRequest) throws URISyntaxException {
        this.timestamp = new Date();
        this.status = status.value();
        this.message = message;
        this.path = new URI(((ServletWebRequest) webRequest).getRequest().getRequestURI()).getPath();

        if (LOG.isDebugEnabled()) {
            debugMessage = Arrays.toString(e.getStackTrace());
        } else {
            debugMessage = null;
        }
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
