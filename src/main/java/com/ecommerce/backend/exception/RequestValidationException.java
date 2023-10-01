package com.ecommerce.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestValidationException extends RuntimeException{
    /**
     * Excepción construida que se lanza cuando se recibe una request que intenta
     * actualizar un producto
     */
    public RequestValidationException(String message){
        super(message);
    }
}
