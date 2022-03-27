package ru.nsu.ccfit.kondakova.factory.Details;

import java.util.concurrent.atomic.AtomicInteger;

public class Auto extends StorageDetail {
    private static AtomicInteger detailNumber = new AtomicInteger(0);
    private BodyDetail bodyDetail;
    private MotorDetail motorDetail;
    private AccessoryDetail accessoryDetail;

    public Auto(BodyDetail bodyDetail, MotorDetail motorDetail, AccessoryDetail accessoryDetail) {
        this.bodyDetail = bodyDetail;
        this.motorDetail = motorDetail;
        this.accessoryDetail = accessoryDetail;

        detailNumber.incrementAndGet();
        id = detailNumber.toString();
    }

    public BodyDetail getBodyDetail() {
        return bodyDetail;
    }

    public MotorDetail getMotorDetail() {
        return motorDetail;
    }

    public AccessoryDetail getAccessoryDetail() {
        return accessoryDetail;
    }
}
