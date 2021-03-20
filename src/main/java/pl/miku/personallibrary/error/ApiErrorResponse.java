package pl.miku.personallibrary.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiErrorResponse implements Serializable  {
    private Integer status;
    private String path;
    private String message;
    private String exception;
    private Date timestamp;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ApiError> errors;

    private ApiErrorResponse(Integer status, String path, String message, String exception) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.exception = exception;
    }

    public static ApiErrorResponse valueOf(Integer status, String path, String message, String exception) {
        return new ApiErrorResponse(status, path, message, exception);
    }

    public ApiErrorResponse() {
    }

    public Integer getStatus() {
        return status;
    }

    public ApiErrorResponse setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ApiErrorResponse setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getException() {
        return exception;
    }

    public ApiErrorResponse setException(String exception) {
        this.exception = exception;
        return this;
    }

    public Date getTimestamp() {
        if(timestamp == null)
            timestamp = new Date();
        return timestamp;
    }

    public ApiErrorResponse setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public List<ApiError> getErrors() {
        if(errors == null)
            errors = new ArrayList<>();
        return errors;
    }

    public ApiErrorResponse setErrors(List<ApiError> errors) {
        this.errors = errors;
        return this;
    }
}
