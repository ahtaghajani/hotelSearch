package com.trivago.hotel.exception;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException() {
        super();
    }

    public OfferNotFoundException(String message) {
        super(message);
    }

    public OfferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfferNotFoundException(Throwable cause) {
        super(cause);
    }

    protected OfferNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
