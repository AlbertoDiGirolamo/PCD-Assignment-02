package reactive.model;

import reactive.utils.ComputedFileImpl;
import reactive.utils.Pair;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ModelImpl implements Model {
    private ComputedFileImpl results;

    public void setup(int limit, int maxL, int numIntervals) {
        this.results = new ComputedFileImpl(limit, maxL, numIntervals);
    }

    @Override
    public ComputedFileImpl getResult() {
        return results;
    }

}
