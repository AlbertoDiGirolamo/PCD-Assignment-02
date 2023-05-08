package vt.utils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class ComputedFileImpl {

    List<Pair<String, Integer>> computedFiles = new ArrayList<>();
    final Comparator<Pair<String, Integer>> comparator = reverseOrder(comparing(Pair::getY));
    private final Set<Pair<String, Integer>> ranking = new TreeSet<>(comparator);
    private final Map<Pair<Integer, Integer>, Integer> filesInRange = new HashMap<>();

    int limit;
    public ComputedFileImpl(int limit, int maxL, int numIntervals) {
        this.limit = limit;
        int range = maxL / (numIntervals - 1);
        int indexRange = 0;
        for (int i = 0; i < numIntervals - 1; i++) {
            filesInRange.put(new Pair<>(indexRange, indexRange + range - 1), 0);
            indexRange += range;
        }
        filesInRange.put(new Pair<>(indexRange, Integer.MAX_VALUE), 0);
    }
    public synchronized void add(Pair<String, Integer> file){
        ranking.add(file);
        for (Pair<Integer, Integer> p : filesInRange.keySet()) {
            if(file.getY()>p.getX() && file.getY()<p.getY()) {
                int c = filesInRange.get(p);
                filesInRange.replace(p, c+1);
            }
        }
    }

    public List<Pair<String, Integer>> getComputedFiles() {
        return computedFiles;
    }

    public List<Pair<String, Integer>> getRanking() {
        return this.ranking.stream().limit(this.limit).collect(Collectors.toList());
    }

    public Map<Pair<Integer, Integer>, Integer> getFilesInRange() {
        return filesInRange;
    }

    public void addAll(SynchronizedList results) {
        computedFiles.addAll(results.getResults());
    }
}
