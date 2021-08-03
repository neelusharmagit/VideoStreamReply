package com.reply.videostream.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VideoExceptionHandler {

    @ExceptionHandler(ValidationFailureException.class)
    public ResponseEntity<ErrorResponse> validationFailureExceptionHandler(
            ValidationFailureException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_01_VALIDATION_FAILURE");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<ErrorResponse> noUserFoundException(
            NoUserFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_02_NO_RECORDS");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorResponse> duplicateUserException(
            DuplicateUserException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_03_ACCOUNT_EXIST");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ErrorResponse> invalidAgeException(
            InvalidAgeException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_04_INVALID_AGE");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globleExcpetionHandler(Exception ex) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("API_05_INTERNAL_SERVER_ERROR");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}