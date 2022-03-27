package ru.nsu.lab2;

import ru.nsu.lab2.commands.Command;
import ru.nsu.lab2.context.Injection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.Properties;
import java.util.logging.Logger;

public class Factory {
    private static Factory factory;
    private Class factoryClass;
    private Map<String, Double> map;
    private Stack<Double> stack;
    private static final Properties prop;
    private final static Logger log = Logger.getLogger(Factory.class.getName());

    static {
            InputStream configProperties = Factory.class.getClassLoader().getResourceAsStream("config.properties");
            prop = new Properties();
        try {
            prop.load(configProperties);
        } catch (IOException e) {
            log.severe("Some exception while loading properties: " + e);
        }
    }

    private Factory() {
        this.map = new HashMap<>();
        this.stack = new Stack<>();
    }

    public static Factory getInstance() {
        if (factory == null) {
            factory = new Factory();
        }
        return factory;
    }

    private void fillCommand(Command command, String[] arguments) throws IllegalAccessException {
        Injection injection;

        Field[] fields = factoryClass.getDeclaredFields();
            for (Field field : fields) {
                injection = field.getDeclaredAnnotation(Injection.class);
                field.setAccessible(true);
                switch (injection.arg()) {
                    case STACK:
                        field.set(command, stack);
                        break;
                    case ARGUMENTS:
                        field.set(command, arguments);
                        break;
                    case VARIABLES:
                        field.set(command, map);
                        break;
                }
            }
    }

    public Command makeCommand(String[] arguments) {
        Command command = null;
        String className;

        try {
            className = prop.getProperty(arguments[0]);

            if (className == null) {
                log.warning("Command not found");
            } else {
                factoryClass = Class.forName(className);
                command = (Command)factoryClass.newInstance();
                fillCommand(command, arguments);
            }
        } catch (ClassNotFoundException ex) {
            log.severe("Class not found");
        } catch (InstantiationException ex) {
            log.severe("Object can't be instantiated");
        } catch (IllegalAccessException ex) {
            log.severe("No access to class");
        }

        return command;
    }

    public Stack<Double> getStack() {
        return stack;
    }
}
