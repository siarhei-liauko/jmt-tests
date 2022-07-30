package com.epam.jmt.test.exception;

public class InvalidTemplateFormatException extends RuntimeException {

    public InvalidTemplateFormatException() {
    }

    public InvalidTemplateFormatException(String message) {
        super(message);
    }

    public InvalidTemplateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTemplateFormatException(Throwable cause) {
        super(cause);
    }

    public InvalidTemplateFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
