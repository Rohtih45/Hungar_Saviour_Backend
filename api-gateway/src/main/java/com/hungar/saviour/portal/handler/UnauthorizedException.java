package com.hungar.saviour.portal.handler;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException (String message){
        super(message);
    }
}
