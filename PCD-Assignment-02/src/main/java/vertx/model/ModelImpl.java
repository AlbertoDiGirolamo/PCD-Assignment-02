package vertx.model;

import vertx.utils.ComputedFileImpl;
import vertx.utils.Pair;
import vertx.utils.SynchronizedList;

public class ModelImpl implements Model {
    private ComputedFileImpl results;

    public void setup(int limit, int maxL, int numIntervals) {
        this.results = new ComputedFileImpl(limit, maxL, numIntervals);
    }

    @Override
    public void addResults(SynchronizedList results) {
        for (Pair<String,Integer> result : results.getResults()) {
            this.results.add(result);
        }
    }

    @Override
    public ComputedFileImpl getResult() {
        return results;
    }

}
