package executors.controller;


import executors.utils.ComputedFileImpl;
import executors.utils.Pair;

import java.io.IOException;

public interface Controller {
    void getReport(String path, int topN, int maxL, int numIntervals) throws IOException;
    ComputedFileImpl getResult();
    void endComputation();
}
