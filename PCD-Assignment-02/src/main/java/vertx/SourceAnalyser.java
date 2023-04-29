package vertx;

import java.io.IOException;

public interface SourceAnalyser {
    void getReport(String path, int topN, int maxL, int numIntervals) throws IOException;

    void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException;
}
