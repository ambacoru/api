package ru.ambaco.cmr.exception;


public class EmailNotVerifiedException  extends  RuntimeException {
    public  EmailNotVerifiedException(String message){
        super(message);
    }
}
