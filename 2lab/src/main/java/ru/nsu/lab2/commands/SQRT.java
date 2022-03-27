package ru.nsu.lab2.commands;

import ru.nsu.lab2.context.Context;
import ru.nsu.lab2.context.Injection;

import java.util.Stack;

public class SQRT implements Command {
    @Injection(arg = Context.STACK)
    private Stack<Double> stack;

    @Injection(arg = Context.ARGUMENTS)
    private String[] arguments;

    @Override
    public void execute() {
        if (arguments.length != 1) {
            log.warning("Invalid number of arguments for operation " + arguments[0]);
        } else if (stack.size() == 0) {
            log.warning("Not enough arguments in stack for operation " + arguments[0]);
        } else {
            if (stack.peek() < 0) {
                log.warning("Invalid value of argument for operation " + arguments[0] +
                                    "\nCannot take square root of a negative number");
            } else {
                stack.push(Math.sqrt(stack.pop()));
            }
        }
    }
}
