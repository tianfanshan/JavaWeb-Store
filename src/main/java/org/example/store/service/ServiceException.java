package org.example.store.service;

public class ServiceException extends Exception{

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message,Throwable ex){
        super(message,ex);
    }
}
