package vertx.controller;


import vertx.utils.ComputedFileImpl;
import vertx.utils.Pair;

import java.io.IOException;

public interface Controller {
    void getReport(String path, int topN, int maxL, int numIntervals) throws IOException;
    void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException;
    ComputedFileImpl getResult();
    void endComputation();
    void addResult(Pair<String, Integer> result) throws InterruptedException;

    void stop();

    void start(int i, String text, int parseInt, int parseInt1, int parseInt2);
}
