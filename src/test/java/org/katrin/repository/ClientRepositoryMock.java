package org.katrin.repository;

import org.katrin.entity.Client;
import org.katrin.repository.dao.ClientRepository;

import java.util.List;

public class ClientRepositoryMock implements ClientRepository {
    private final List<Client> clients;

    public ClientRepositoryMock(List<Client> games) {
        this.clients = games;
    }

    @Override
    public Client add(Client client) {
        client.setId(clients.getLast().getId() + 1);
        clients.add(client);
        return client;
    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public Client getByContactDataAndPassword(Client client) {
        return clients.get(clients.indexOf(client));
    }
}
