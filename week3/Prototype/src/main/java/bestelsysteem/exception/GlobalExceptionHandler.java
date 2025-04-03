package bestelsysteem.exception;

import bestelsysteem.model.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APICallException.class)
    public ResponseEntity<ErrorObject> handleApiCallException(APICallException e) {
        return new ResponseEntity<>(e.getErrorObject(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception e) {
        return new ResponseEntity<>(new ErrorObject(e.getMessage(), "GeneralException", "An unexpected error occurred."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
