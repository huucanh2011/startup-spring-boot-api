package vn.luvina.startup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import vn.luvina.startup.dto.ErrorMessage;

@RestControllerAdvice
public class NullPointerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage nullPoiterExceptionHandler(Exception ex,  WebRequest request) {
        return new ErrorMessage(10100, "Thao tác không thành công");
    }

}
