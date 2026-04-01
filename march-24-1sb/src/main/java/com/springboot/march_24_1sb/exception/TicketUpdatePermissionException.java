package com.springboot.march_24_1sb.exception;

public class TicketUpdatePermissionException extends RuntimeException {
    public TicketUpdatePermissionException(String message) {
        super(message);
    }
}
