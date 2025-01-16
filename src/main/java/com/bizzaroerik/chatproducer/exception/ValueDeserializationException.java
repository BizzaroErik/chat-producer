package com.bizzaroerik.chatproducer.exception;

public class ValueDeserializationException extends Exception {

    public static final long serialVersionUID = 1L;

    /**
     * @param message
     * @param throwable
     */
    public ValueDeserializationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
