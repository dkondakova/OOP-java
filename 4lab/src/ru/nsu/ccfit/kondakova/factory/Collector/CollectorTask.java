package ru.nsu.ccfit.kondakova.factory.Collector;

import ru.nsu.ccfit.kondakova.factory.Details.AccessoryDetail;
import ru.nsu.ccfit.kondakova.factory.Details.BodyDetail;
import ru.nsu.ccfit.kondakova.factory.Details.MotorDetail;
import ru.nsu.ccfit.kondakova.threadpool.Task;

public class CollectorTask implements Task {
    Collector collector;

    public CollectorTask(Collector collector) {
        this.collector = collector;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void performWork() throws InterruptedException {
        BodyDetail bodyDetail = collector.getBodyStorage().poll();
        MotorDetail motorDetail = collector.getMotorStorage().poll();
        AccessoryDetail accessoryDetail = collector.getAccessoryStorage().poll();

        collector.getAutoStorage().addAuto(bodyDetail, motorDetail, accessoryDetail);
    }
}

