package com.kytc.bikeID.exception;

public class AuthenticationCodeNotSendException extends RuntimeException {
    public AuthenticationCodeNotSendException(String message) {

        super(message);
    }

    public AuthenticationCodeNotSendException(Throwable cause) {

        super(cause);
    }

}
