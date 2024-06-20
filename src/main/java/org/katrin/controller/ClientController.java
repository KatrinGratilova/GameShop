package org.katrin.controller;

import org.katrin.MenuOption;
import org.katrin.MenuMessages;
import org.katrin.entity.Client;
import org.katrin.service.ClientService;

import java.io.PrintStream;
import java.util.Scanner;

public class ClientController {
    ClientService clientService;
    PrintStream out;
    Scanner in;

    public ClientController(ClientService clientService, PrintStream out, Scanner in) {
        this.clientService = clientService;
        this.out = out;
        this.in = in;
    }

    public MenuOption signIn() {
        return () -> {
            Client.ClientBuilder builder = Client.builder();
            out.print(MenuMessages.CONTACT_DATA.getMessage());
            builder.contactData(in.nextLine());
            out.print(MenuMessages.PASSWORD.getMessage());
            builder.password(in.nextLine());
            out.printf(MenuMessages.WELCOME.getMessage(), clientService.findClient(builder.build()).getFullName());
        };
    }

    public MenuOption signUp() {
        return () -> {
            Client.ClientBuilder builder = Client.builder();
            out.print(MenuMessages.FULL_NAME.getMessage());
            builder.fullName(in.nextLine());
            out.print(MenuMessages.CONTACT_DATA.getMessage());
            builder.contactData(in.nextLine());
            out.print(MenuMessages.PASSWORD.getMessage());
            builder.password(in.nextLine());
            out.printf(MenuMessages.WELCOME.getMessage(), clientService.createClient(builder.build()).getFullName());
        };
    }
}
