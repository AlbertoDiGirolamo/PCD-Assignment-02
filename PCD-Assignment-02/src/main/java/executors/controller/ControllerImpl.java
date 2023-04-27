package executors.controller;

import executors.model.Folder;
import executors.model.LinesCounter;
import executors.model.Model;
import executors.utils.ComputedFileImpl;
import executors.utils.Pair;
import executors.utils.SynchronizedList;
import executors.view.View;

import java.io.File;
import java.io.IOException;

public class ControllerImpl implements Controller{
    private final Model model;
    private final View view;

    SynchronizedList results = new SynchronizedList();

    public ControllerImpl(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void start(String path, int topN, int maxL, int numIntervals) throws IOException, InterruptedException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));
        LinesCounter lc = new LinesCounter();
        results = lc.countOccurrencesInParallel(folder);
        Thread.sleep(2000);
        this.model.addResults(results);
        Thread.sleep(2000);
        this.endComputation();
    }

    @Override
    public void processEvent(Runnable runnable){
        new Thread(runnable).start();
    }

    @Override
    public ComputedFileImpl getResult() {
        return this.model.getResult();
    }

    public void addResult(Pair<String, Integer> result) {
        this.model.getResult().add(result);
    }

    @Override
    public void stop() {

    }

    @Override
    public void updateResult() throws InterruptedException {
        this.view.resultsUpdated();
    }

    @Override
    public void endComputation() {
        this.view.endComputation();
    }
}
