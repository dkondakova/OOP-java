package ru.nsu.ccfit.kondakova.threadpool;

public interface Task {
    String getName();
    void performWork() throws InterruptedException;
}

