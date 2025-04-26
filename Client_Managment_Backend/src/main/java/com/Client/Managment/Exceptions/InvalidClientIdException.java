package com.Client.Managment.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This class is used to handle the exception when the client id is invalid.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidClientIdException extends RuntimeException{

    public InvalidClientIdException(String message){
        super(message);
    }
}
