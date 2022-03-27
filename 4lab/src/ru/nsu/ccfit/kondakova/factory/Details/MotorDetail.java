package ru.nsu.ccfit.kondakova.factory.Details;

import java.util.concurrent.atomic.AtomicInteger;

public class MotorDetail extends StorageDetail {
    static AtomicInteger detailNumber = new AtomicInteger(0);

    public MotorDetail() {
        detailNumber.incrementAndGet();
        id = detailNumber.toString();
    }
}
