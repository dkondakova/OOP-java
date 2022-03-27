package ru.nsu.ccfit.kondakova.factory.Details;

import java.util.concurrent.atomic.AtomicInteger;

public class BodyDetail extends StorageDetail {
    private static AtomicInteger detailNumber = new AtomicInteger(0);

    public BodyDetail() {
        detailNumber.incrementAndGet();
        id = detailNumber.toString();
    }
}
