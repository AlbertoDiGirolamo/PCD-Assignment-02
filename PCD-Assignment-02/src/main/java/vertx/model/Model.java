package vertx.model;

import vertx.utils.ComputedFileImpl;
import vertx.utils.SynchronizedList;

public interface Model {
    ComputedFileImpl getResult();
    void setup(int limit, int maxL, int numIntervals);

    void addResults(SynchronizedList results);
}
