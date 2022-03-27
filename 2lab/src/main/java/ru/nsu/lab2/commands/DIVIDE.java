package ru.nsu.lab2.commands;

import ru.nsu.lab2.context.Context;
import ru.nsu.lab2.context.Injection;

import java.util.Stack;

public class DIVIDE implements Command {
    @Injection(arg = Context.STACK)
    private Stack<Double> stack;

    @Injection(arg = Context.ARGUMENTS)
    private String[] arguments;

    @Override
    public void execute() {
        if (arguments.length != 1) {
            log.warning("Invalid number of arguments for operation " + arguments[0]);
        } else if (stack.size() < 2) {
            log.warning("Not enough arguments in stack for operation " + arguments[0]);
        } else {
            double tmp = stack.pop();
            if (stack.peek() == 0) {
                log.warning("Invalid value of argument for operation " + arguments[0] +
                                    "\nDivision by zero");
            } else {
                stack.push(tmp / stack.pop());
            }
        }
    }
}
