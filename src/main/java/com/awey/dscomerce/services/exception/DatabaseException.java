package com.awey.dscomerce.services.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String mensagem){
        super(mensagem);
    }
}
