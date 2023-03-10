package gr.kyrkosma.exception.handler;

import gr.kyrkosma.exception.TransactionAmountIsZeroException;
import gr.kyrkosma.exception.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //An example of how to handle a custom exception
    @ExceptionHandler(value = {TransactionAmountIsZeroException.class})
    protected ResponseEntity<Object> handleZeroAmount(Exception ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse = new ExceptionResponse(httpStatus.value(), "Transaction amount can't be zero.", System.currentTimeMillis());

        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), httpStatus, request);
    }

}
