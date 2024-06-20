package org.katrin.exception;

import java.io.IOException;

public class ClientAlreadyExistsException extends IOException {
    public ClientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

