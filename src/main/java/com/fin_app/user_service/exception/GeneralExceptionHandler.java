package com.fin_app.user_service.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @Autowired
    private Environment environment;
    @ExceptionHandler({ BadRequestException.class, ValidationException.class
            , IllegalArgumentException.class,UnsupportedOperationException.class})
    public ResponseEntity<ErrorMessage> generalExceptionHandler(Exception ex) {
        ErrorMessage error =  ErrorMessage.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(environment.getProperty(UserServiceConstants.GENERAL_EXCEPTION_MESSAGE.toString()))
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**************************************************************************************************************
     * Handler for validation failures w.r.t to DTOs
     * @param ex
     * @return ErrorMessage object
     **************************************************************************************************************/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ErrorMessage error =ErrorMessage.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(",")))
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    /**************************************************************************************************************
     * Handler for validation failures w.r.t to U.R.I parameters
     * @param ex
     * @return ErrorMessage object
     **************************************************************************************************************/
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        ErrorMessage error = ErrorMessage.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

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

    @ExceptionHandler({DuplicateRecordExist.class})
    public ResponseEntity<ErrorMessage> duplicateRecordExists (DuplicateRecordExist ex) {

        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.CONFLICT.value());
        error.setMessage("The record already exist please verify and enter new record"+ ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }
}
