package com.fin_app.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({Exception.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> generalExceptionHandler(Exception ex) {
        ErrorMessage error =  ErrorMessage.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred: " + ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorMessage> noDataFoundExceptionHandler(NoDataFoundException ex) {

        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

//    @ExceptionHandler(NoResourceFoundException.class)
//    public ResponseEntity<ErrorMessage> noResourceFoundExceptionHandler(NoResourceFoundException ex) {
//
//        ErrorMessage error = new ErrorMessage();
//        error.setErrorCode(HttpStatus.NOT_FOUND.value());
//        error.setMessage("no resourcse found.Please check uri is valid or not");
//
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//
//    }

    @ExceptionHandler(DuplicateRecordExist.class)
    public ResponseEntity<ErrorMessage> duplicateRecordExists (DuplicateRecordExist ex) {

        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.CONFLICT.value());
        error.setMessage("The record already exist please verify and enter new record"+ ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }
}
