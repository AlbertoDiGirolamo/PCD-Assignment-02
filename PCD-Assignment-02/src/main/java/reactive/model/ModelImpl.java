package reactive.model;

import reactive.utils.ComputedFileImpl;
import reactive.utils.Pair;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModelImpl implements Model {
    private ComputedFileImpl results;
    private final AtomicBoolean stopExecution = new AtomicBoolean();

    public void setup(int limit, int maxL, int numIntervals) {
        this.results = new ComputedFileImpl(limit, maxL, numIntervals);
    }

    public AtomicBoolean getStop(){
        return this.stopExecution;
    }

    public void resetStop(){
        this.stopExecution.set(false);
    }
    @Override
    public ComputedFileImpl getResult() {
        return results;
    }

}
