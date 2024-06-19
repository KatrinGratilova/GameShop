package org.katrin.repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.Messages;
import org.katrin.entity.Client;
import org.katrin.exception.ClientDoesNotExist;

public class ClientRepository {
    private final SessionFactory sessionFactory;

    private final String selectWhereContactDataAndPassword = """
            SELECT c
            FROM Client c 
            WHERE password = :password AND contactData = :contactData""";

    public ClientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Client add(Client client) throws ClientAlreadyExistsException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(client);
            session.getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            session.getTransaction().rollback();
            System.err.println(ex.getMessage());
            throw new ClientAlreadyExistsException(Messages.CLIENT_ALREADY_EXISTS.getMessage(), ex.getCause());
        } finally {
            session.close();
        }
        return client;
    }

    public Client getById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Client client = session.get(Client.class, id);
        session.getTransaction().commit();
        session.close();
        return client;
    }

    public Client getByContactDataAndPassword(Client client) throws ClientDoesNotExist {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(selectWhereContactDataAndPassword);
        query.setParameter("password", client.getPassword());
        query.setParameter("contactData", client.getContactData());

        Client foundClient;
        try {
            foundClient = (Client) query.getSingleResult();
            session.getTransaction().commit();
        } catch (NoResultException ex) {
            session.getTransaction().rollback();
            System.err.println(ex.getMessage());
            throw new ClientDoesNotExist(Messages.CLIENT_DOES_NOT_EXISTS.getMessage(), ex.getCause());
        } finally {
            session.close();
        }

        return foundClient;
    }


}
