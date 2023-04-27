package executors.utils;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedList {
    public List<Pair<String, Integer>> getResults() {
        return results;
    }

    private List<Pair<String, Integer>> results = new ArrayList<>();

    public synchronized void add(Pair<String, Integer> file){
        results.add(file);
    }

    public synchronized void addAll(SynchronizedList join) {
        results.addAll(join.results);
    }
}
