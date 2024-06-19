package org.katrin.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.katrin.GameMessages;
import org.katrin.MenuOption;
import org.katrin.entity.Game;
import org.katrin.service.GameService;

import java.awt.*;
import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

public class GameController {
    Scanner in;
    PrintStream out;
    GameService service;

    public GameController(Scanner in, PrintStream out, GameService service) {
        this.in = in;
        this.out = out;
        this.service = service;
    }

    public MenuOption showAllGames() {
        return () -> service.findAll().forEach(out::println);
    }

    public MenuOption addNewGame() {
        return () -> {
            Game.GameBuilder builder = Game.builder();

            out.print(GameMessages.NAME.getMessage());
            builder.name(in.nextLine());
            out.print(GameMessages.TYPE.getMessage());
            builder.type(in.nextLine());
            out.print(GameMessages.RELEASE_DATE.getMessage());
            builder.releaseDate(Date.valueOf(in.nextLine()));
            out.print(GameMessages.RATING.getMessage());
            builder.rating(Integer.parseInt(in.nextLine()));
            out.print(GameMessages.COST.getMessage());
            builder.cost(Double.parseDouble(in.nextLine()));
            out.print(GameMessages.DESCRIPTION.getMessage());
            builder.description(in.nextLine());

            Game game = service.create(builder.build());
            out.printf(GameMessages.ADDED_GAME.getMessage(), game.getId());
        };
    }

    public MenuOption deleteGame(){
        return () -> {
            this.showAllGames();
            out.println(GameMessages.INPUT_ID_TO_DELETE.getMessage());
            service.deleteById(Integer.parseInt(in.nextLine()));
        };
    }

    public MenuOption filterByName() {
        return () -> {
            out.println(GameMessages.NAME.getMessage());
            service.findByName(in.nextLine()).forEach(out::println);
        };
    }

    public MenuOption filterByCost() {
        return () -> {
            out.println(GameMessages.MIN_COST.getMessage());
            out.println(GameMessages.MAX_COST.getMessage());
            service.findByCostRange(Double.parseDouble(in.nextLine()), Double.parseDouble(in.nextLine())).forEach(out::println);
        };
    }

    public MenuOption filterByType() {
        return () -> {
            out.println(GameMessages.TYPE.getMessage());
            service.findByType(in.nextLine()).forEach(out::println);
        };
    }

    public MenuOption sortByCreationDate() {
        return () -> {
            service.sortByCreationDate().forEach(out::println);
        };
    }
}
