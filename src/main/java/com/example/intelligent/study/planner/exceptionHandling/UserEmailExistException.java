package com.example.intelligent.study.planner.exceptionHandling;

public class UserEmailExistException extends Exception{
    public UserEmailExistException(String message){
        super(message);
    }
}
