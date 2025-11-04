package com.awey.dscomerce.services.exception;

public class ForbidenException extends RuntimeException{
    public ForbidenException(String mensagem){
        super(mensagem);
    }
}
