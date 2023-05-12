package reactive;

import io.reactivex.rxjava3.core.Observable;
import reactive.utils.Pair;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface SourceAnalyser {
    void getReport(String path, int topN, int maxL, int numIntervals) throws IOException, ExecutionException, InterruptedException;

    void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException;
}
