package com.hungar.saviour.portal.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiGatewayHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unAuthorizedException(UnauthorizedException unauthorizedException){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
