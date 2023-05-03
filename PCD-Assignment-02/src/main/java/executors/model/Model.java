package executors.model;

import executors.utils.ComputedFileImpl;
import executors.utils.Pair;
import executors.utils.SynchronizedList;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface Model {
    ComputedFileImpl getResult();
    void setup(int limit, int maxL, int numIntervals);

    void addResults(Future<SynchronizedList> results) throws ExecutionException, InterruptedException;
}
