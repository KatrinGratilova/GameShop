package org.katrin.exception;

import java.io.IOException;

public class ClientDoesNotExist extends IOException {
    public ClientDoesNotExist(String message, Throwable cause){
        super(message, cause);
    }
}
