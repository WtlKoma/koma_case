package com.wtl.koma.crypto.exception;


public class CryptoRuntimeException extends RuntimeException{

    private static final long serialVersionUID = 4307324535036972998L;

    public CryptoRuntimeException(String message) {
        super(message);
    }

    public CryptoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
