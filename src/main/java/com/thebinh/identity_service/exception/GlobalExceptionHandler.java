package com.thebinh.identity_service.exception;

import com.thebinh.identity_service.domain.DTO.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<RestResponse<Object>> handleIdInvalid(Exception e){
        RestResponse res = new RestResponse();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(e.getMessage());
        res.setMessage("400 - BadRequest");
        res.setData("no data here");
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(value = UsernameInvalidException.class)
    public ResponseEntity<RestResponse<Object>> handleUsernameInvalid(Exception e){
        RestResponse res = new RestResponse();
        res.setData("no data here");
        res.setMessage("400-BadRequest");
        res.setError(e.getMessage());
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleMethodArgInvalid(MethodArgumentNotValidException e){
        RestResponse<Object> res = new RestResponse();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        List<FieldError> listErrors = e.getFieldErrors();
        List<String> errors = listErrors.stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        res.setError("400 - bad request");
        res.setData("no data here");
        res.setMessage(errors.size() > 1 ? errors : errors.get(0));
        return ResponseEntity.badRequest().body(res);
    }
}
