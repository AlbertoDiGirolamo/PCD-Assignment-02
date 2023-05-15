package reactive.model;

import reactive.utils.ComputedFileImpl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public interface Model {
    ComputedFileImpl getResult();
    void setup(int limit, int maxL, int numIntervals);

    AtomicBoolean getStop();

    void resetStop();
}
