package org.katrin.service;

import org.junit.Assert;
import org.junit.Test;
import org.katrin.entity.Client;
import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.exception.EntityInstanceDoesNotExist;
import org.katrin.repository.ClientRepositoryMock;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceTest {
    @Test
    public void createClient_Ok() throws ClientAlreadyExistsException {
        List<Client> clients = new ArrayList<>(List.of(Client.builder()
                .id(1)
                .fullName("Client 1")
                .contactData("0872437788")
                .password("1234")
                .build()));
        ClientService clientService = new ClientService(new ClientRepositoryMock(clients));

        Client newClient = Client.builder()
                .fullName("Client 2")
                .contactData("0873456778")
                .password("568904")
                .build();
        clientService.createClient(newClient);

        Assert.assertTrue(clients.contains(newClient));
        Assert.assertEquals(2, clients.size());
        Assert.assertEquals(2, clients.getLast().getId());
    }

    @Test
    public void findClient_Ok() throws EntityInstanceDoesNotExist {
        Client expected = Client.builder()
                .id(1)
                .fullName("Client 1")
                .contactData("0872437788")
                .password("1234")
                .build();
        List<Client> clients = new ArrayList<>(List.of(expected));
        ClientService clientService = new ClientService(new ClientRepositoryMock(clients));

        Client actual = clientService.findClient(expected);

        Assert.assertEquals(expected, actual);
    }
}
