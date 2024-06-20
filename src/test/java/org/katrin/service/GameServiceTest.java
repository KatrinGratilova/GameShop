package org.katrin.service;

import org.junit.Assert;
import org.junit.Test;
import org.katrin.entity.Game;
import org.katrin.repository.GameRepositoryMock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GameServiceTest {
    @Test
    public void create_Ok() {
        List<Game> games = new ArrayList<>(List.of(Game.builder()
                .id(1)
                .name("Game 1")
                .type("Logical")
                .build()));
        GameService clientService = new GameService(new GameRepositoryMock(games));

        Game newGame = Game.builder()
                .id(1)
                .name("Game 2")
                .type("Logical")
                .build();
        clientService.create(newGame);

        Assert.assertTrue(games.contains(newGame));
        Assert.assertEquals(2, games.size());
        Assert.assertEquals(2, games.getLast().getId());
    }

    @Test
    public void deleteById_Ok(){
        Game game = Game.builder()
                .id(1)
                .name("Game 1")
                .type("Logical")
                .build();
        List<Game> games = new ArrayList<>(List.of(game));
        GameService clientService = new GameService(new GameRepositoryMock(games));

        clientService.deleteById(game.getId());

        Assert.assertFalse(games.contains(game));
        Assert.assertEquals(0, games.size());
    }

    @Test
    public void findByName_Ok(){
        Game expected = Game.builder()
                .id(1)
                .name("Game 1")
                .type("Logical")
                .build();
        List<Game> games = new ArrayList<>(List.of(expected, Game.builder()
                .id(1)
                .name("Game 2")
                .type("Logical")
                .build()));
        GameService clientService = new GameService(new GameRepositoryMock(games));

        List<Game> actual = clientService.findByName(expected.getName());

        Assert.assertTrue(actual.contains(expected));
        Assert.assertEquals(1, actual.size());
    }

    @Test
    public void findByCostRange_Ok(){
        Game expected = Game.builder()
                .id(1)
                .name("Game 1")
                .cost(23)
                .build();
        List<Game> games = new ArrayList<>(List.of(expected, Game.builder()
                .id(1)
                .name("Game 2")
                .cost(12)
                .build()));
        GameService clientService = new GameService(new GameRepositoryMock(games));

        List<Game> actual = clientService.findByCostRange(20, 30);

        Assert.assertTrue(actual.contains(expected));
        Assert.assertEquals(1, actual.size());
    }

    @Test
    public void findByType_Ok() {
        Game expected = Game.builder()
                .id(1)
                .name("Game 1")
                .type("Logical")
                .build();
        List<Game> games = new ArrayList<>(List.of(expected, Game.builder()
                .id(1)
                .name("Game 2")
                .type("Casual")
                .build()));
        GameService clientService = new GameService(new GameRepositoryMock(games));

        List<Game> actual = clientService.findByType(expected.getType());

        Assert.assertTrue(actual.contains(expected));
        Assert.assertEquals(1, actual.size());
    }

    @Test
    public void sortByCreationDate_Ok() {
        List<Game> games = new ArrayList<>(List.of(
                Game.builder()
                .id(1)
                .name("Game 1")
                .creationDate(Date.valueOf("2024-12-12"))
                .build(),
                Game.builder()
                .id(2)
                .name("Game 2")
                .creationDate(Date.valueOf("2023-03-06"))
                .build()));
        GameService clientService = new GameService(new GameRepositoryMock(new ArrayList<>(games)));

        List<Game> expected = new ArrayList<>(List.of(
                Game.builder()
                        .id(2)
                        .name("Game 2")
                        .creationDate(Date.valueOf("2023-03-06"))
                        .build(),
                Game.builder()
                        .id(1)
                        .name("Game 1")
                        .creationDate(Date.valueOf("2024-12-12"))
                        .build()));
        List<Game> actual = clientService.sortByCreationDate();

        Assert.assertNotEquals(games, actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAll_Ok() {
        List<Game> expected = new ArrayList<>(List.of(Game.builder()
                .id(1)
                .name("Game 1")
                .type("Casual")
                .build()));
        GameService clientService = new GameService(new GameRepositoryMock(expected));

        List<Game> actual = clientService.findAll();

        Assert.assertEquals(expected, actual);
    }
}
