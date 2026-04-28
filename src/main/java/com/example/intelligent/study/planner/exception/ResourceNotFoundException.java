package com.example.intelligent.study.planner.exception;

public class ResourceNotFoundException extends Exception{
    private static final String message = "Resource Not Found";
    public ResourceNotFoundException(){
        super(message);
    }
}
