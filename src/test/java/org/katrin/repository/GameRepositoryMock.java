package org.katrin.repository;

import org.katrin.entity.Game;
import org.katrin.repository.dao.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GameRepositoryMock implements GameRepository {
    private List<Game> games;

    public GameRepositoryMock(List<Game> games) {
        this.games = games;
    }

    @Override
    public Game add(Game game) {
        game.setId(games.getLast().getId() + 1);
        games.add(game);
        return game;
    }

    @Override
    public void deleteById(int id) {
    }

    @Override
    public List<Game> getByName(String name) {
        return games.stream().filter(g -> g.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Game> getByCostRange(double min, double max) {
        return null;
    }

    @Override
    public List<Game> getByType(String type) {
        return null;
    }

    @Override
    public List<Game> sortByCreationDate() {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }
}
