package com.example.SplitMate.Exceptions;

import com.example.SplitMate.DTOs.ExceptionDto;

public class EmailAlreadyRegisteredException extends Exception {

    public  EmailAlreadyRegisteredException(String messagee) {
        super(messagee);
    }
}
