package org.katrin.service;

import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.entity.Client;
import org.katrin.exception.ClientDoesNotExist;
import org.katrin.repository.ClientRepository;

public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository){
        this.repository = repository;
    }

    public void createClient(Client client) throws ClientAlreadyExistsException {
        Client addedClient = repository.add(client);
    }

    public Client findClient(Client client) throws ClientDoesNotExist {
        return repository.getByContactDataAndPassword(client);
    }
}
