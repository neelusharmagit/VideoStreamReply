package com.reply.videostream.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class VideoExceptionHandlerTest {
    private VideoExceptionHandler videoExceptionHandler;

    @BeforeEach
    public void setUp() {
        videoExceptionHandler = new VideoExceptionHandler();
    }
    @Test
    public void testHandleNoUserFoundException() {
        NoUserFoundException noRecordFoundException = new NoUserFoundException("No Users Available");

        ResponseEntity<ErrorResponse> responseEntity = videoExceptionHandler
                .noUserFoundException(noRecordFoundException);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);

        assertEquals(responseEntity.getBody().getErrorMessage(),"No Users Available");
        assertEquals(responseEntity.getBody().getErrorCode(), "API_02_NO_RECORDS");
    }

    @Test
    public void testHandlValidateFailureException() {
        ValidationFailureException validationFailureException = new ValidationFailureException("Incorrect User Details");

        ResponseEntity<ErrorResponse> responseEntity = videoExceptionHandler
                .validationFailureExceptionHandler(validationFailureException);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        assertEquals(responseEntity.getBody().getErrorMessage(),"Incorrect User Details");
        assertEquals(responseEntity.getBody().getErrorCode(), "API_01_VALIDATION_FAILURE");
    }


    @Test
    public void testHandleDuplicateUserException() {
        DuplicateUserException duplicateUserException = new DuplicateUserException("User Already exist");

        ResponseEntity<ErrorResponse> responseEntity = videoExceptionHandler
                .duplicateUserException(duplicateUserException);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CONFLICT);

        assertEquals(responseEntity.getBody().getErrorMessage(),"User Already exist");
        assertEquals(responseEntity.getBody().getErrorCode(), "API_03_ACCOUNT_EXIST");
    }


    @Test
    public void testHandleInValidAgeException() {
        InvalidAgeException invalidAgeException = new InvalidAgeException("InValid Age");

        ResponseEntity<ErrorResponse> responseEntity = videoExceptionHandler
                .invalidAgeException(invalidAgeException);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.FORBIDDEN);

        assertEquals(responseEntity.getBody().getErrorMessage(),"InValid Age");
        assertEquals(responseEntity.getBody().getErrorCode(), "API_04_INVALID_AGE");
    }


    @Test
    public void testHandlGlobleExcpetionHandler() {
        Exception exception = mock(Exception.class);

        ResponseEntity<ErrorResponse> responseEntity = videoExceptionHandler
                .globleExcpetionHandler(exception);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(responseEntity.getBody().getErrorCode(), "API_05_INTERNAL_SERVER_ERROR");
    }

}
