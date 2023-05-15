package reactive;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import reactive.utils.ComputedFileImpl;
import reactive.utils.Pair;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface SourceAnalyser {
    Single<ComputedFileImpl> getReport(String path, int topN, int maxL, int numIntervals) throws IOException, ExecutionException, InterruptedException;

    Flowable<ComputedFileImpl> analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException;
}
