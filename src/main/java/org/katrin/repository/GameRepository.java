package org.katrin.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.katrin.entity.Game;
import java.util.List;

public class GameRepository {
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

    public GameRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public Game add(Game game){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(game);
        session.getTransaction().commit();
        session.close();
        return game;
    }

    public void deleteById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(id);
        session.getTransaction().commit();
        session.close();
    }

    public List<Game> getByName(String name){
        try(Session session = sessionFactory.openSession()){
            Query<Game> query = session.createQuery(selectWhereName);
            query.setParameter("name", name);
            return query.list();
        }
    }

    public List<Game> getByCostRange(double min, double max){
        try(Session session = sessionFactory.openSession()){
            Query<Game> query = session.createQuery(selectWhereCostBetween);
            query.setParameter("min", min);
            query.setParameter("max", max);
            return query.list();
        }
    }

    public List<Game> getByType(String type){
        try(Session session = sessionFactory.openSession()){
            Query<Game> query = session.createQuery(selectWhereType);
            query.setParameter("type", type);
            return query.list();
        }
    }

    public List<Game> sortByCreationDate(){
        try(Session session = sessionFactory.openSession()){
            Query<Game> query = session.createQuery(selectSortedByCreationDate);
            return query.list();
        }
    }

    public List<Game> getAll(){
        try(Session session = sessionFactory.openSession()){
            Query<Game> query = session.createQuery(selectAll);
            return query.list();
        }
    }
}
