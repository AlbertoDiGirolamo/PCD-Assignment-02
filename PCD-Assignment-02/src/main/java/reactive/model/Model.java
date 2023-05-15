package reactive.model;

import reactive.utils.ComputedFileImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface Model {
    ComputedFileImpl getResult();
    void setup(int limit, int maxL, int numIntervals);

}
