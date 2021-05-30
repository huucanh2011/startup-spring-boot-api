package vn.luvina.startup.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
        .collect(Collectors.toList());
    ApiError apiError = new ApiError(status.value(), errors);
    return new ResponseEntity<>(apiError, headers, status);
  }

  @ExceptionHandler({ AccessDeniedException.class })
  public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
    List<String> errors = Arrays.asList(ex.getMessage());
    ApiError apiError = new ApiError(HttpStatus.FORBIDDEN.value(), errors);
    return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
  }

}
