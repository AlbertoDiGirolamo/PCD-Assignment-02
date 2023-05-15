package vt.model;

import vt.utils.ComputedFileImpl;
import vt.utils.SynchronizedList;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface Model {
    ComputedFileImpl getResult();
    void setup(int limit, int maxL, int numIntervals);

    void addResults(Future<SynchronizedList> results) throws ExecutionException, InterruptedException;

    CompletableFuture<Void> getStopExecution();
}
