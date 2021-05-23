package vn.luvina.startup.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler({ ServiceRuntimeException.class })
  public ResponseEntity<ApiError> handlerServiceRuntimeException(ServiceRuntimeException ex) {
    List<String> errors = Arrays.asList(ex.getMessage());
    ApiError apiError = new ApiError(ex.getHttpStatus().value(), errors);
    return new ResponseEntity<>(apiError, ex.getHttpStatus());
  }

}
