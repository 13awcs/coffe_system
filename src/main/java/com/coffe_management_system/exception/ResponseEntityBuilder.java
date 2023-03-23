package com.coffe_management_system.exception;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError){
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }
}
