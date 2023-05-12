package reactive.controller;


import io.reactivex.rxjava3.core.Observable;
import reactive.utils.ComputedFileImpl;
import reactive.utils.Pair;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface Controller {
    void getReport(String path, int topN, int maxL, int numIntervals) throws IOException, ExecutionException, InterruptedException;
    void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException;
    ComputedFileImpl getResult();

    void addResult(Pair<String, Integer> file) throws InterruptedException;

    void stop();
}
