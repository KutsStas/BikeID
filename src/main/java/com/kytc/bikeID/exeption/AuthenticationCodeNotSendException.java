package com.kytc.bikeID.exeption;

public class AuthenticationCodeNotSendException extends RuntimeException {
    public AuthenticationCodeNotSendException(String message) {

        super(message);
    }

    public AuthenticationCodeNotSendException(Throwable cause) {

        super(cause);
    }

}
