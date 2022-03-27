package ru.nsu.ccfit.kondakova.factory.Dealers;

import ru.nsu.ccfit.kondakova.factory.Storages.AutoStorage;

import java.util.LinkedList;

public class AutoSale implements Runnable {
    private final int DEALER_COUNT;
    private int dealersDelay;
    private AutoStorage autoStorage;
    private LinkedList<Dealer> dealers;

    public AutoSale(AutoStorage autoStorage, int DEALER_COUNT, int dealersDelay) {
        this.DEALER_COUNT = DEALER_COUNT;
        this.dealersDelay = dealersDelay;
        this.autoStorage = autoStorage;
        this.dealers = new LinkedList<>();
    }

    @Override
    public void run() {
        for (int i = 0; i < DEALER_COUNT; i++) {
            Dealer dealer = new Dealer(autoStorage, dealersDelay);
            dealers.add(dealer);
            new Thread(dealer, String.valueOf(i)).start();
        }
    }

    public void setDealersDelay(int dealersDelay) {
        this.dealersDelay = dealersDelay;

        for (Dealer dealer: dealers){
            dealer.setDelay(dealersDelay);
        }
    }
}
