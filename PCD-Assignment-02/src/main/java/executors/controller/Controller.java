package executors.controller;


import executors.utils.ComputedFileImpl;
import executors.utils.Pair;

import java.io.File;
import java.io.IOException;

public interface Controller {
    void start(String path, int topN, int maxL, int numIntervals) throws IOException, InterruptedException;
    void processEvent(Runnable runnable);
    ComputedFileImpl getResult();
    void addResult(Pair<String, Integer> result);

    void stop();

    void updateResult() throws InterruptedException;
    void endComputation();
}
