package ru.nsu.lab2.commands;

import ru.nsu.lab2.Factory;

import java.util.logging.Logger;

public interface Command {
    Logger log = Logger.getLogger(Factory.class.getName());

    void execute();
}
