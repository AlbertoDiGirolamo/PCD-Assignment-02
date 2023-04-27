package executors.utils;

import java.io.File;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class ComputedFileImpl {

    List<Pair<String, Integer>> computedFiles = new ArrayList<>();
    int limit;
    int maxL;
    int numIntervals;
    public ComputedFileImpl(int limit, int maxL, int numIntervals) {
        this.limit = limit;
        this.maxL = maxL;
        this.numIntervals = numIntervals;
    }
    public synchronized void add(Pair<String, Integer> file){
        computedFiles.add(file);
    }

    public List<Pair<String, Integer>> getComputedFiles() {
        return computedFiles;
    }

    public void addAll(SynchronizedList results) {
        computedFiles.addAll(results.getResults());
    }

    public List<Pair<String, Integer>> getRanking() {

    }

    public Map<Pair<Integer, Integer>, Integer> getFilesInRange() {
        try{
            this.mutex.lock();
            return this.filesInRange;
        }finally {
            this.mutex.unlock();
        }
    }
}
