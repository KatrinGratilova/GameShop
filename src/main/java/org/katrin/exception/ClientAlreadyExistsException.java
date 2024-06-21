package org.katrin.exception;

import java.sql.SQLException;

public class ClientAlreadyExistsException extends SQLException {
    public ClientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

