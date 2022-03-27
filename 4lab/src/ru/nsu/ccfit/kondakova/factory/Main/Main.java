package ru.nsu.ccfit.kondakova.factory.Main;

import ru.nsu.ccfit.kondakova.factory.Collector.Collector;
import ru.nsu.ccfit.kondakova.factory.Collector.CollectorController;
import ru.nsu.ccfit.kondakova.factory.Dealers.AutoSale;
import ru.nsu.ccfit.kondakova.factory.Details.AccessoryDetail;
import ru.nsu.ccfit.kondakova.factory.Details.BodyDetail;
import ru.nsu.ccfit.kondakova.factory.Details.MotorDetail;
import ru.nsu.ccfit.kondakova.factory.Storages.AutoStorage;
import ru.nsu.ccfit.kondakova.factory.Storages.Storage;
import ru.nsu.ccfit.kondakova.factory.UI.UserInterface;

public class Main {
    public static void main(String[] args) {
        ConfigParser parser = new ConfigParser();

        Storage<BodyDetail> bodyStorage = new Storage<>(parser.getBodyStorageSize(),
                parser.getBodySupplierMinDelay(),
                BodyDetail.class);
        bodyStorage.addSupplier();


        Storage<MotorDetail> motorStorage = new Storage<>(parser.getMotorStorageSize(),
                parser.getMotorSupplierMinDelay(),
                MotorDetail.class);
        motorStorage.addSupplier();

        Storage<AccessoryDetail> accessoryStorage = new Storage<>(parser.getAccessoryStorageSize(),
                parser.getAccessorySupplierMinDelay(),
                AccessoryDetail.class);
        for (int i = 0; i < parser.getAccessorySuppliers(); i++) {
            accessoryStorage.addSupplier();
        }

        AutoStorage autoStorage = new AutoStorage(parser.getAutoStorageSize());
        Collector collector = new Collector(bodyStorage, motorStorage, accessoryStorage, autoStorage);
        AutoSale autoSale = new AutoSale(autoStorage, parser.getDealers(), parser.getDealerMinDelay());

        new Thread(new UserInterface(collector,autoSale, parser)).start();
        new Thread(new CollectorController(collector)).start();
        new Thread(autoSale).start();
    }
}
