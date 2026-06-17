package com.jaideep.ecommerce.exceptions;

/**
 * Application-level exception for the e-commerce domain.
     * Thrown whenever a business-rule violation or recoverable error occurs
     * so the UI layer can display a user-friendly message without crashing.
     */
public class EcommerceException extends RuntimeException {

    public EcommerceException(String message) {
                super(message);
    }

    /**
     * Use this constructor when wrapping a lower-level exception
         * (e.g. I/O errors from the persistence layer).
         */
    public EcommerceException(String message, Throwable cause) {
                super(message, cause);
    }
}
