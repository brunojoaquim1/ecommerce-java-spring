package com.awey.dscomerce.services;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String mensagem){
        super(mensagem);
    }
}
