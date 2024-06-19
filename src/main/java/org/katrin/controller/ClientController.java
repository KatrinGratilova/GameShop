package org.katrin.controller;

import org.katrin.MenuOption;
import org.katrin.Messages;
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
            out.print(Messages.CONTACT_DATA.getMessage());
            builder.contactData(in.nextLine());
            out.print(Messages.PASSWORD.getMessage());
            builder.password(in.nextLine());
            out.printf(Messages.WELCOME.getMessage(), clientService.findClient(builder.build()).getFullName());
        };
    }

    public MenuOption signUp() {
        return () -> {
            Client.ClientBuilder builder = Client.builder();
            out.print(Messages.FULL_NAME.getMessage());
            builder.fullName(in.nextLine());
            out.print(Messages.CONTACT_DATA.getMessage());
            builder.contactData(in.nextLine());
            out.print(Messages.PASSWORD.getMessage());
            builder.password(in.nextLine());
            clientService.createClient(builder.build());
            out.printf(Messages.WELCOME.getMessage(), clientService.findClient(builder.build()).getFullName());
        };
    }
}
