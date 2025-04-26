package com.Client.Managment.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This class is used to handle the exception when a client is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String message) {
        super(message);
    }
}
