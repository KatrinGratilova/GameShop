package org.katrin.repository.dao;

import org.katrin.entity.Game;
import java.util.List;

public interface GameRepository {
    public Game add(Game game);

    public void deleteById(int id);

    public List<Game> getByName(String name);

    public List<Game> getByCostRange(double min, double max);

    public List<Game> getByType(String type);

    public List<Game> sortByCreationDate();

    public List<Game> getAll();
}
