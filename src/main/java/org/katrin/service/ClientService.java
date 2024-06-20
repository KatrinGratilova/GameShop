package org.katrin.service;

import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.entity.Client;
import org.katrin.exception.ClientDoesNotExist;
import org.katrin.repository.dao.ClientRepository;

public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository){
        this.repository = repository;
    }

    public Client createClient(Client client) throws ClientAlreadyExistsException {
        return repository.add(client);
    }

    public Client findClient(Client client) throws ClientDoesNotExist {
        return repository.getByContactDataAndPassword(client);
    }
}
