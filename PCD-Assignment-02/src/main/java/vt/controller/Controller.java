package vt.controller;

import vt.SourceAnalyser;
import vt.model.Folder;
import vt.model.FolderSearchTask;
import vt.model.Model;
import vt.utils.ComputedFileImpl;
import vt.utils.Pair;
import vt.view.View;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Controller implements SourceAnalyser {
    private final Model model;
    private final View view;
    private Thread initThread = null;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public CompletableFuture<Void> getReport(String path, int topN, int maxL, int numIntervals) throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<Void> computationEnd = new CompletableFuture<>();
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));

        Thread.ofVirtual().start(() -> {
            Thread initThread = null;
            try {
                initThread = Thread.ofVirtual().unstarted(new FolderSearchTask(folder, this));
                initThread.start();
                initThread.join();
                computationEnd.complete(null);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return computationEnd;

    }

    @Override
    public void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));

        initThread = Thread.ofVirtual().unstarted(new FolderSearchTask(folder, this));
        initThread.start();

    }

    public ComputedFileImpl getResult() {
        return this.model.getResult();
    }

    public synchronized void addResult(Pair<String, Integer> result) throws InterruptedException {
        this.model.getResult().add(result);
        this.view.resultsUpdated();

    }

    public void stop(){
    }



}
