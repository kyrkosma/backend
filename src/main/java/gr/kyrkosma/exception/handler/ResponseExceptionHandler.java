package gr.kyrkosma.exception.handler;

import gr.kyrkosma.exception.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleNegativeBalance(Exception ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(httpStatus.value(), "Something went wrong.", System.currentTimeMillis());

        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    protected ResponseEntity<Object> handleIllegalStateException(Exception ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(httpStatus.value(), ex.getMessage(), System.currentTimeMillis());

        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), httpStatus, request);
    }

}
