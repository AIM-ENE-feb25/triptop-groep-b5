package confidentiality.controller;

import confidentiality.exception.APICallException;
import confidentiality.model.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthControllerExceptionHandler {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler
  public ResponseEntity<ErrorObject> APICallExceptionHandler(APICallException ex) {
    return new ResponseEntity<>(ex.getErrorObject(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
