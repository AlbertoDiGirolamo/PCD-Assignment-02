package executors;

import java.io.IOException;

public interface SourceAnalyser {
    void getReport(String path, int topN, int maxL, int numIntervals) throws IOException;

    //void analyzeSources(Directory d)
}
