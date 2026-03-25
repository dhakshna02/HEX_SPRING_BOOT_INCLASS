package com.springboot.march_24_1sb.exception;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String message) {
        super(message);
    }
}
