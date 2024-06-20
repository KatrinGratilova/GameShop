package org.katrin.exception;

import java.io.IOException;

public class EntityInstanceDoesNotExist extends IOException {
    public EntityInstanceDoesNotExist(String message, Throwable cause){
        super(message, cause);
    }
}
