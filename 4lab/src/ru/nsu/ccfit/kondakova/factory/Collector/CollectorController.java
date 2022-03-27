package ru.nsu.ccfit.kondakova.factory.Collector;

public class CollectorController implements Runnable {
    private Collector collector;

    public CollectorController(Collector collector) {
        this.collector = collector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                collector.getAutoStorage().isEmpty();
                collector.collectAuto();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
