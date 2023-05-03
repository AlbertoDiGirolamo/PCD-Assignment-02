package executors.model;

import executors.utils.ComputedFileImpl;
import executors.utils.Pair;
import executors.utils.SynchronizedList;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class ModelImpl implements Model{
    private ComputedFileImpl results;

    public void setup(int limit, int maxL, int numIntervals) {
        this.results = new ComputedFileImpl(limit, maxL, numIntervals);
    }

    @Override
    public void addResults(Future<SynchronizedList> results) throws ExecutionException, InterruptedException {
        for (Pair<String,Integer> result : results.get().getResults()) {
            this.results.add(result);
        }
    }

    @Override
    public ComputedFileImpl getResult() {
        return results;
    }

}
