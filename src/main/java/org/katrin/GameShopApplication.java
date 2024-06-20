package org.katrin;

import org.katrin.controller.ClientController;
import org.katrin.controller.GameController;
import org.katrin.exception.ClientAlreadyExistsException;
import org.katrin.exception.EntityInstanceDoesNotExist;
import org.katrin.repository.ClientRepositoryImpl;
import org.katrin.repository.GameRepositoryImpl;
import org.katrin.service.ClientService;
import org.katrin.service.GameService;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class GameShopApplication {
    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);
    ClientRepositoryImpl clientRepository = new ClientRepositoryImpl(SessionFactorySingleton.getSessionFactory());
    ClientService clientService = new ClientService(clientRepository);
    ClientController clientController = new ClientController(clientService, out, in);
    GameRepositoryImpl gameRepository = new GameRepositoryImpl(SessionFactorySingleton.getSessionFactory());
    GameService gameService = new GameService(gameRepository);
    GameController gameController = new GameController(in, out, gameService);

    public void main(String[] args) {
        GameShopApplication main = new GameShopApplication();
        main.userAuthorization();
        main.optionSelection();
    }

    public void userAuthorization() {
        Map<Integer, MenuOption> authorizationOptions = Map.of(
                1, clientController.signIn(),
                2, clientController.signUp());

        int option;
        do {
            out.print(MenuMessages.AUTHORIZATION_OPTIONS.getMessage());
            option = getOption(authorizationOptions);
        }
        while (option <= 0 || option > authorizationOptions.size());
    }

    public void optionSelection() {
        Map<Integer, MenuOption> menuOptions = Map.of(
                1, gameController.showAllGames(),
                2, gameController.addNewGame(),
                3, gameController.deleteGame(),
                4, gameController.filterByName(),
                5, gameController.filterByCost(),
                6, gameController.filterByType(),
                7, gameController.sortByCreationDate(),
                8, gameController.exit());

        int option;
        do {
            out.print(MenuMessages.MENU_OPTIONS.getMessage());
            option = getOption(menuOptions);
        }
        while (option <= 0 || option > menuOptions.size() || option != menuOptions.size());
    }

    private int getOption(Map<Integer, MenuOption> menuOptions) {
        int option;
        out.print(MenuMessages.OPTION.getMessage());
        option = Integer.parseInt(in.nextLine());
        try {
            menuOptions.getOrDefault(option, () -> out.println(MenuMessages.INVALID_OPTION.getMessage())).optionAction();
        } catch (ClientAlreadyExistsException | EntityInstanceDoesNotExist e) {
            out.println(e.getMessage());
            option = 0;
        }
        return option;
    }
}