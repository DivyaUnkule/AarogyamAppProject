package com.app.custom_exception_handler;



@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    

    public ResourceNotFoundException(String message) {
        super(message);
    }

    
}
