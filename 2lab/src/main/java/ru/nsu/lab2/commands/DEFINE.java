package ru.nsu.lab2.commands;

import ru.nsu.lab2.context.Context;
import ru.nsu.lab2.context.Injection;

import java.util.Map;

public class DEFINE implements Command {
    @Injection(arg = Context.VARIABLES)
    private Map<String,Double> map;

    @Injection(arg = Context.ARGUMENTS)
    private String[] arguments;

    @Override
    public void execute() {
        try {
            if (arguments.length != 3) {
                System.out.println("Invalid number of arguments for operation " + arguments[0]);
            } else {
                map.put(arguments[1], Double.parseDouble(arguments[2]));
            }
        } catch (NumberFormatException ex) {
            log.warning("Invalid value for operation " + arguments[0]);
        }
    }
}
