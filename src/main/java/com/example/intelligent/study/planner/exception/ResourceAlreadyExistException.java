package com.example.intelligent.study.planner.exception;

public class ResourceAlreadyExistException extends RuntimeException {
    private static final String message = "Resource Already Exist";
    public ResourceAlreadyExistException(){
        super(message);
    }
}
