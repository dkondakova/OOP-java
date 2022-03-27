package ru.nsu.ccfit.kondakova.factory.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigParser {
    private static final Properties p;
    private final static Logger log = Logger.getLogger(ConfigParser.class.getName());

    private int bodyStorageSize;
    private int motorStorageSize;
    private int accessoryStorageSize;
    private int autoStorageSize;

    private int bodySupplierMinDelay;
    private int motorSupplierMinDelay;
    private int accessorySupplierMinDelay;
    private int dealerMinDelay;

    private int accessorySuppliers;
    private int workers;
    private int dealers;

    private boolean logFile;

    static {
        try {
            InputStream resourceAsStream = ConfigParser.class.getClassLoader().getResourceAsStream("config.properties");
            p = new Properties();
            p.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public ConfigParser() {
        String value = null;

        value = p.getProperty("BodyStorageSize");
        if (value == null) {
            writeLog();
        } else {
            bodyStorageSize = Integer.valueOf(value);
        }

        value = p.getProperty("MotorStorageSize");
        if (value == null) {
            writeLog();
        } else {
            motorStorageSize = Integer.valueOf(value);
        }

        value = p.getProperty("AccessoryStorageSize");
        if (value == null) {
            writeLog();
        } else {
            accessoryStorageSize = Integer.valueOf(value);
        }

        value = p.getProperty("AutoStorageSize");
        if (value == null) {
            writeLog();
        } else {
            autoStorageSize = Integer.valueOf(value);
        }

        value = p.getProperty("BodySupplierMinDelay");
        if (value == null) {
            writeLog();
        } else {
            bodySupplierMinDelay = Integer.valueOf(value);
        }
        
        value = p.getProperty("MotorSupplierMinDelay");
        if (value == null) {
            writeLog();
        } else {
            motorSupplierMinDelay = Integer.valueOf(value);
        }

        value = p.getProperty("AccessorySupplierMinDelay");
        if (value == null) {
            writeLog();
        } else {
            accessorySupplierMinDelay = Integer.valueOf(value);
        }

        value = p.getProperty("DealerMinDelay");
        if (value == null) {
            writeLog();
        } else {
            dealerMinDelay = Integer.valueOf(value);
        }

        value = p.getProperty("AccessorySuppliers");
        if (value == null) {
            writeLog();
        } else {
            accessorySuppliers = Integer.parseInt(value);
        }

        value = p.getProperty("Workers");
        if (value == null) {
            writeLog();
        } else {
            workers = Integer.valueOf(value);
        }

        value = p.getProperty("Dealers");
        if (value == null) {
            writeLog();
        } else {
            dealers = Integer.valueOf(value);
        }

        value = p.getProperty("LogFile");
        if (value == null) {
            writeLog();
        } else {
            logFile = Boolean.valueOf(value);
        }
    }
    
    public void writeLog() {
        log.severe("Couldn't read value from properties file");
    }

    public int getBodyStorageSize() {
        return bodyStorageSize;
    }

    public int getMotorStorageSize() {
        return motorStorageSize;
    }

    public int getAccessoryStorageSize() {
        return accessoryStorageSize;
    }

    public int getAutoStorageSize() {
        return autoStorageSize;
    }

    public int getBodySupplierMinDelay() {
        return bodySupplierMinDelay;
    }

    public int getMotorSupplierMinDelay() {
        return motorSupplierMinDelay;
    }

    public int getAccessorySupplierMinDelay() {
        return accessorySupplierMinDelay;
    }

    public int getDealerMinDelay() {
        return dealerMinDelay;
    }

    public int getAccessorySuppliers() {
        return accessorySuppliers;
    }

    public int getWorkers() {
        return workers;
    }

    public int getDealers() {
        return dealers;
    }

    public boolean isLogFile() {
        return logFile;
    }
}


