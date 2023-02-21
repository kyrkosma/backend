package gr.kyrkosma.exception.handler;

import gr.kyrkosma.exception.AccountBalanceIsNegativeException;
import gr.kyrkosma.exception.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //An example of how to handle a custom exception
    @ExceptionHandler(value = {AccountBalanceIsNegativeException.class})
    protected ResponseEntity<Object> handleNegativeBalance(RuntimeException ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(httpStatus.value(), "Account balance can't be negative.", System.currentTimeMillis());

        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), httpStatus, request);
    }

}
