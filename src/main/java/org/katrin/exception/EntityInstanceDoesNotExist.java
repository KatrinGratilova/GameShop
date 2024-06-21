package org.katrin.exception;

import java.sql.SQLException;

public class EntityInstanceDoesNotExist extends SQLException {
    public EntityInstanceDoesNotExist(String message, Throwable cause){
        super(message, cause);
    }
}
