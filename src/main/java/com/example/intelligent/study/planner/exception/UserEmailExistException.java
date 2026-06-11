package com.example.intelligent.study.planner.exception;

public class UserEmailExistException extends RuntimeException{
    public UserEmailExistException(String message){
        super(message);
    }
}
