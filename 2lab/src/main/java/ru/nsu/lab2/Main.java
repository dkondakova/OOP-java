package ru.nsu.lab2;

import ru.nsu.lab2.commands.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    private final static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Factory factory = Factory.getInstance();
        String arguments;
        Command command;

        try {
            BufferedReader reader;
            if (args.length != 0) {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            } else {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            while ((arguments = reader.readLine()) != null) {
                if (arguments.charAt(0) == '#') {
                    if (args.length != 0) {
                        System.out.println(arguments);
                    }
                    continue;
                }
                command = factory.makeCommand(arguments.split(" "));
                if (command != null) {
                    command.execute();
                }
            }
        } catch (IOException ex) {
            log.severe("Error: " + ex);
        }
    }
}