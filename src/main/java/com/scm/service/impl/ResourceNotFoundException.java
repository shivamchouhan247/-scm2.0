package com.scm.service.impl;

public class ResourceNotFoundException extends RuntimeException {

    ResourceNotFoundException(String msg){
        super(msg);
    }
}
