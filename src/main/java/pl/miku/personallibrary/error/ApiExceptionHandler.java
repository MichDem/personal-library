package pl.miku.personallibrary.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleNotfoundException(Exception ex, WebRequest request) {
        return prepareRestException(ex, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> prepareRestException(Exception ex, WebRequest request, HttpStatus status) {
        logger.info(ex.getClass().getCanonicalName());
        var error = ex.getLocalizedMessage();
        if(ex.getCause() != null) {
            error += String.format(". %s", ex.getCause().getMessage());
        } else if(error.isEmpty()) {
            error = "message not avaliable";
        }
        var response = ApiErrorResponse.valueOf(status.value(), getPath(request), error, ex.getClass().getCanonicalName());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();
    }
}
