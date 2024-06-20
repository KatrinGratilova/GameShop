package org.katrin.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.katrin.entity.Game;
import org.katrin.repository.dao.GameRepository;

import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    private final SessionFactory sessionFactory;
    private final String selectAll = """
            SELECT g
            FROM Game g
            """;
    private final String selectWhereName = """
            SELECT g
            FROM Game g
            WHERE name = :name
            """;
    private final String selectWhereCostBetween = """
            SELECT g
            FROM Game g
            WHERE cost BETWEEN :min AND :max
            """;
    private final String selectWhereType = """
            SELECT g
            FROM Game g
            WHERE type = :type
            """;
    private final String selectSortedByCreationDate = """
            SELECT g
            FROM Game g
            ORDER BY creationDate
            """;

    public GameRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Game add(Game game) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(game);
        session.getTransaction().commit();
        session.close();
        return game;
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Game game = session.get(Game.class, id);
        session.remove(game);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Game> getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Game> query = session.createQuery(selectWhereName);
            query.setParameter("name", name);
            return query.list();
        }
    }

    @Override
    public List<Game> getByCostRange(double min, double max) {
        try (Session session = sessionFactory.openSession()) {
            Query<Game> query = session.createQuery(selectWhereCostBetween);
            query.setParameter("min", min);
            query.setParameter("max", max);
            return query.list();
        }
    }

    @Override
    public List<Game> getByType(String type) {
        try (Session session = sessionFactory.openSession()) {
            Query<Game> query = session.createQuery(selectWhereType);
            query.setParameter("type", type);
            return query.list();
        }
    }

    @Override
    public List<Game> sortByCreationDate() {
        try (Session session = sessionFactory.openSession()) {
            Query<Game> query = session.createQuery(selectSortedByCreationDate);
            return query.list();
        }
    }

    @Override
    public List<Game> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Game> query = session.createQuery(selectAll);
            return query.list();
        }
    }
}