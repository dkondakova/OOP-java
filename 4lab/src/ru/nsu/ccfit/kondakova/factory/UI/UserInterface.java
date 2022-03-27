package ru.nsu.ccfit.kondakova.factory.UI;

import ru.nsu.ccfit.kondakova.factory.Collector.Collector;
import ru.nsu.ccfit.kondakova.factory.Dealers.AutoSale;
import ru.nsu.ccfit.kondakova.factory.Main.ConfigParser;

import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame implements Runnable {
    private Collector collector;
    private ConfigParser configParser;
    private AutoSale autoSale;
    private final int DELAY = 3;
    private final int LABELS_COUNT = 10;

    public UserInterface(Collector collector, AutoSale autoSale, ConfigParser configParser) {
        this.collector = collector;
        this.autoSale = autoSale;
        this.configParser = configParser;
    }

    private JSlider initSlider(int min, int max, int current) {
        JSlider slider = new JSlider(min, max, current);
        slider.setOrientation(JSlider.VERTICAL);
        slider.setMajorTickSpacing(min);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(50, 200));
        return slider;
    }

    private Container initSliderContainer(JSlider slider, JLabel label) {
        Container container = new Container();
        container.setLayout(new BorderLayout());
        container.add(slider, BorderLayout.NORTH);
        container.add(label, BorderLayout.SOUTH);

        return container;
    }

    private JLabel initSliderLabel(String name) {
        JLabel label = new JLabel(name);
        label.setPreferredSize(new Dimension(50, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        return label;
    }

    private JLabel initInfoLabel(String name) {
        JLabel label = new JLabel(name);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        return label;
    }

    @Override
    public void run() {
        setMinimumSize(new Dimension(600, 250));
        setTitle("Config");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        Container infoContainer = new Container();
        Container slidersContainer = new Container();
        Container currentOccupancyContainer = new Container();
        Container totalProducedContainer = new Container();

        slidersContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        currentOccupancyContainer.setLayout(new BoxLayout(currentOccupancyContainer, BoxLayout.Y_AXIS));
        totalProducedContainer.setLayout(new BoxLayout(totalProducedContainer, BoxLayout.Y_AXIS));
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));

        int bodyDelay = configParser.getBodySupplierMinDelay();
        JSlider bodySupplierDelaySlider = initSlider(bodyDelay, bodyDelay * LABELS_COUNT, bodyDelay);
        slidersContainer.add(initSliderContainer(bodySupplierDelaySlider, initSliderLabel("Body")));

        int motorDelay = configParser.getMotorSupplierMinDelay();
        JSlider motorSupplierDelaySlider = initSlider(motorDelay, motorDelay * LABELS_COUNT, motorDelay);
        slidersContainer.add(initSliderContainer(motorSupplierDelaySlider, initSliderLabel("Motor")));

        int accessoryDelay = configParser.getAccessorySupplierMinDelay();
        JSlider accessorySupplierDelaySlider = initSlider(accessoryDelay, accessoryDelay * LABELS_COUNT, accessoryDelay);
        slidersContainer.add(initSliderContainer(accessorySupplierDelaySlider, initSliderLabel("Acc-ry")));

        int dealerDelay = configParser.getDealerMinDelay();
        JSlider dealerDelaySlider = initSlider(dealerDelay, dealerDelay * LABELS_COUNT, dealerDelay);
        slidersContainer.add(initSliderContainer(dealerDelaySlider, initSliderLabel("Dealer")));

        JLabel currentBodyOccupancy = initInfoLabel("Body: " + collector.getBodyStorage().getCurrentOccupancy());
        JLabel currentMotorOccupancy = initInfoLabel("Motor: " + collector.getMotorStorage().getCurrentOccupancy());
        JLabel currentAccessoryOccupancy = initInfoLabel("Accessory: " + collector.getAccessoryStorage().getCurrentOccupancy());
        JLabel currentAutoOccupancy = initInfoLabel("Autos: " + collector.getAutoStorage().getCurrentOccupancy());

        currentOccupancyContainer.add(initInfoLabel("Storage occupancy: "));
        currentOccupancyContainer.add(currentBodyOccupancy);
        currentOccupancyContainer.add(currentMotorOccupancy);
        currentOccupancyContainer.add(currentAccessoryOccupancy);
        currentOccupancyContainer.add(currentAutoOccupancy);

        JLabel totalBodyProduced = initInfoLabel("Body: " + collector.getBodyStorage().getTotalProduced());
        JLabel totalMotorProduced = initInfoLabel("Motor: " + collector.getMotorStorage().getTotalProduced());
        JLabel totalAccessoryProduced = initInfoLabel("Accessory: " + collector.getAccessoryStorage().getTotalProduced());
        JLabel totalAutoProduced = initInfoLabel("Autos: " + collector.getAutoStorage().getTotalProduced());

        totalProducedContainer.add(initInfoLabel("Total Produced"));
        totalProducedContainer.add(totalBodyProduced);
        totalProducedContainer.add(totalMotorProduced);
        totalProducedContainer.add(totalAccessoryProduced);
        totalProducedContainer.add(totalAutoProduced);

        this.add(slidersContainer);
        this.add(currentOccupancyContainer);
        this.add(totalProducedContainer);

        bodySupplierDelaySlider.addChangeListener(e -> {
            int value = ((JSlider) e.getSource()).getValue();
            collector.getBodyStorage().setDelay(value);
        });

        motorSupplierDelaySlider.addChangeListener(e -> {
            int value = ((JSlider) e.getSource()).getValue();
            collector.getMotorStorage().setDelay(value);
        });

        accessorySupplierDelaySlider.addChangeListener(e -> {
            int value = ((JSlider) e.getSource()).getValue();
            collector.getAccessoryStorage().setDelay(value);
        });

        dealerDelaySlider.addChangeListener(e -> {
            int value = ((JSlider) e.getSource()).getValue();
            autoSale.setDealersDelay(value);
        });

        new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    currentBodyOccupancy.setText("Body: " + collector.getBodyStorage().getCurrentOccupancy());
                    currentMotorOccupancy.setText("Motor: " + collector.getMotorStorage().getCurrentOccupancy());
                    currentAccessoryOccupancy.setText("Accessory: " + collector.getAccessoryStorage().getCurrentOccupancy());
                    currentAutoOccupancy.setText("Auto: " + collector.getAutoStorage().getCurrentOccupancy());


                    totalBodyProduced.setText("Body: " + collector.getBodyStorage().getTotalProduced());
                    totalMotorProduced.setText("Motor: " + collector.getMotorStorage().getTotalProduced());
                    totalAccessoryProduced.setText("Accessory: " + collector.getAccessoryStorage().getTotalProduced());
                    totalAutoProduced.setText("Auto: " + collector.getAutoStorage().getTotalProduced());

                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        setVisible(true);
        pack();
    }
}
