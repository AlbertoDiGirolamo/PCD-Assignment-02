package executors.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SynchronizedList implements Future<SynchronizedList> {
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

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public SynchronizedList get() throws InterruptedException, ExecutionException {
        return this;
    }

    @Override
    public SynchronizedList get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
