package ru.nsu.ccfit.kondakova.factory.Details;

import java.util.concurrent.atomic.AtomicInteger;

public class AccessoryDetail extends StorageDetail {
    private static AtomicInteger detailNumber = new AtomicInteger(0);

    public AccessoryDetail() {
        detailNumber.incrementAndGet();
        id = detailNumber.toString();
    }
}
