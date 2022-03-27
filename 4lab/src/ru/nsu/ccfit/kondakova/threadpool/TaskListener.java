package ru.nsu.ccfit.kondakova.threadpool;

public interface TaskListener {
    void taskInterrupted(Task t);
    void taskFinished(Task t);
    void taskStarted(Task t);
}
