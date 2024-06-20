package org.katrin.service;

import org.junit.Assert;
import org.junit.Test;
import org.katrin.entity.Game;
import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.exception.ClientDoesNotExist;
import org.katrin.repository.GameRepositoryMock;

import java.util.ArrayList;
import java.util.List;

public class GameServiceTest {
    @Test
    public void create_Ok() throws ClientAlreadyExistsException {
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
    public void findByName_Ok() throws ClientDoesNotExist {
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
}
