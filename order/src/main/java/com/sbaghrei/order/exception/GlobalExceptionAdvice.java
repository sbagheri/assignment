package com.sbaghrei.order.exception;

import com.sbaghrei.order.model.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<BaseResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse(exception.getMessage(), "404"));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<BaseResponse> handleBadRequestException(EmptyResultDataAccessException exception) {
        String msg = "resource not found with the given id";
        log.error(msg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse(msg, "404"));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse> handleBadRequestException(ResourceNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(exception.getMessage(), "400"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleInternalServerException(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(exception.getMessage(), "500"));
    }
}
