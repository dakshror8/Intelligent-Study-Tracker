package com.example.intelligent.study.planner.exception;

public class ResourceAlreadyExistException extends Exception {
    private static final String message = "Resource Already Exist";
    public ResourceAlreadyExistException() {
        super(message);
    }
}
