package org.katrin;

import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.exception.ClientDoesNotExist;

@FunctionalInterface
public interface MenuOption {
    public void optionAction() throws ClientAlreadyExistsException, ClientDoesNotExist;
}
