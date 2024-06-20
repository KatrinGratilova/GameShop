package org.katrin.repository.dao;

import org.katrin.entity.Client;
import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.exception.ClientDoesNotExist;


public interface ClientRepository {
    Client add(Client client) throws ClientAlreadyExistsException;

    Client getById(int id);

    Client getByContactDataAndPassword(Client client) throws ClientDoesNotExist;
}
