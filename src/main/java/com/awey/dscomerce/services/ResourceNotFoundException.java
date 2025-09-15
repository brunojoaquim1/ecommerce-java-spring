package com.awey.dscomerce.services;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
}
