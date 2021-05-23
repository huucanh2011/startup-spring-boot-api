package vn.luvina.startup.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ServiceRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final HttpStatus httpStatus;

  public ServiceRuntimeException(HttpStatus httpStatus, String errorMessage) {
    super(errorMessage);
    this.httpStatus = httpStatus;
  }
}
