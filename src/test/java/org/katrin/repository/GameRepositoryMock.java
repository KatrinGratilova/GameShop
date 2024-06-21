package org.katrin.repository;

import org.katrin.entity.Game;
import org.katrin.repository.dao.GameRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameRepositoryMock implements GameRepository {
    private final List<Game> games;

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
        games.remove(id - 1);
    }

    @Override
    public List<Game> getByName(String name) {
        return games.stream().filter(g -> g.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Game> getByCostRange(double min, double max) {
        return games.stream().filter(g -> g.getCost() >= min && g.getCost() <= max).collect(Collectors.toList());
    }

    @Override
    public List<Game> getByType(String type) {
        return games.stream().filter(g -> g.getType().equals(type)).collect(Collectors.toList());
    }

    @Override
    public List<Game> sortByCreationDate() {
        games.sort(Comparator.comparing(Game::getCreationDate));
        return games;
    }

    @Override
    public List<Game> getAll() {
        return games;
    }
}
