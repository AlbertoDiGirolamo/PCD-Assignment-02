package executors.controller;

import executors.SourceAnalyser;
import executors.model.Folder;
import executors.model.FolderSearchTask;
import executors.model.Model;
import executors.utils.ComputedFileImpl;
import executors.utils.Pair;
import executors.utils.SynchronizedList;
import executors.view.View;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Controller implements SourceAnalyser {
    private final Model model;
    private final View view;
    private ForkJoinPool forkJoinPool = new ForkJoinPool();

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void getReport(String path, int topN, int maxL, int numIntervals) throws IOException, ExecutionException, InterruptedException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));

        forkJoinPool.submit(new FolderSearchTask(folder, this)).join().get();
        this.view.endComputation();


    }

    @Override
    public void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));
        forkJoinPool.submit(new FolderSearchTask(folder, this));
    }

    public ComputedFileImpl getResult() {
        return this.model.getResult();
    }

    public synchronized void addResult(Pair<String, Integer> result) throws InterruptedException {
        this.model.getResult().add(result);
        this.view.resultsUpdated();

    }

    public void stop() {
        forkJoinPool.shutdownNow();
        forkJoinPool = new ForkJoinPool();
    }


}
