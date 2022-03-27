package ru.nsu.ccfit.kondakova.factory.Storages;

import ru.nsu.ccfit.kondakova.factory.Details.AccessoryDetail;
import ru.nsu.ccfit.kondakova.factory.Details.BodyDetail;
import ru.nsu.ccfit.kondakova.factory.Details.Auto;
import ru.nsu.ccfit.kondakova.factory.Details.MotorDetail;

import java.util.LinkedList;

public class AutoStorage {
    private final int CAPACITY;
    private LinkedList<Auto> autos;
    private int currentOccupancy;
    private int totalProduced;

    public AutoStorage(int CAPACITY) {
        this.totalProduced = 0;
        this.currentOccupancy = 0;
        this.CAPACITY = CAPACITY;
        autos = new LinkedList<>();
    }

    public void addAuto(BodyDetail bodyDetail, MotorDetail motorDetail, AccessoryDetail accessoryDetail) throws InterruptedException {
        synchronized (this) {

            while (currentOccupancy >= CAPACITY) {
                wait();
            }

            autos.add(new Auto(bodyDetail, motorDetail, accessoryDetail));
            currentOccupancy++;
            totalProduced++;
            notifyAll();
        }
    }

    public Auto getAuto() throws InterruptedException {
        synchronized (this) {
            while (currentOccupancy == 0) {
                wait();
            }

            Auto auto = autos.removeFirst();
            currentOccupancy--;

            notifyAll();

            return auto;
        }
    }

    public void isEmpty() throws InterruptedException {
        synchronized (this) {
            while (currentOccupancy == CAPACITY) {
                wait();
            }
        }
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public int getTotalProduced() {
        return totalProduced;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }
}
