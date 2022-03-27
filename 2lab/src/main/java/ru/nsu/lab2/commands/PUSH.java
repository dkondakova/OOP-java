package ru.nsu.lab2.commands;

import ru.nsu.lab2.context.Context;
import ru.nsu.lab2.context.Injection;

import java.util.Map;
import java.util.Stack;

public class PUSH implements Command {
    @Injection(arg = Context.STACK)
    private Stack<Double> stack;

    @Injection(arg = Context.VARIABLES)
    private Map<String,Double> map;

    @Injection(arg = Context.ARGUMENTS)
    private String[] arguments;

    @Override
    public void execute() {
        try {
            if (arguments.length != 2) {
                log.warning("Invalid number of arguments for operation " + arguments[0]);
            } else {
                stack.push(Double.parseDouble(arguments[1]));
            }
        } catch (NumberFormatException ex) {
            if (map.containsKey(arguments[1])) {
                stack.push(map.get(arguments[1]));
            } else {
                log.warning("Invalid value of argument for operation " + arguments[0]);
            }
        }
    }
}
