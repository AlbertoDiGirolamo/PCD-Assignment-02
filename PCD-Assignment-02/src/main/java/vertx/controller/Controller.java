package vertx.controller;


import vertx.utils.ComputedFileImpl;
import vertx.utils.Pair;

import java.io.IOException;

public interface Controller {
    void getReport(String path, int topN, int maxL, int numIntervals) throws IOException;
    ComputedFileImpl getResult();
    void endComputation();
    void addResult(Pair<String, Integer> result);

}
