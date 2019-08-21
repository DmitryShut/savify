package com.shut.savify.util;

import com.shut.savify.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity handleNotFoundException(Exception ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
