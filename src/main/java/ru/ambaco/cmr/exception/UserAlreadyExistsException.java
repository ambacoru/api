package ru.ambaco.cmr.exception;

public class UserAlreadyExistsException  extends RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
