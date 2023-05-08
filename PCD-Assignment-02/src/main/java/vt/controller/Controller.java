package vt.controller;


import vt.utils.ComputedFileImpl;
import vt.utils.Pair;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface Controller {
    CompletableFuture<Void> getReport(String path, int topN, int maxL, int numIntervals) throws IOException, ExecutionException, InterruptedException;
    void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException;
    ComputedFileImpl getResult();

    void addResult(Pair<String, Integer> file) throws InterruptedException;

    void stop();
    void endComputation();
}
