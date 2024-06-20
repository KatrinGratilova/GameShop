package org.katrin;

import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.exception.EntityInstanceDoesNotExist;

@FunctionalInterface
public interface MenuOption {
    void optionAction() throws ClientAlreadyExistsException, EntityInstanceDoesNotExist;
}
