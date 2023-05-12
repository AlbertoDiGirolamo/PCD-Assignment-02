package reactive.controller;

import io.reactivex.rxjava3.core.Observable;
import reactive.SourceAnalyser;
import reactive.model.Folder;
import reactive.model.FolderSearchTask;
import reactive.model.Model;
import reactive.utils.ComputedFileImpl;
import reactive.utils.Pair;
import reactive.utils.SynchronizedList;
import reactive.view.View;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ControllerImpl implements Controller, SourceAnalyser {
    private final Model model;
    private final View view;

    public ControllerImpl(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void getReport(String path, int topN, int maxL, int numIntervals) throws IOException, ExecutionException, InterruptedException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));

        Observable<Pair<String, Integer>> source = Observable.create(emitter -> {
            Thread initThread = new Thread(new FolderSearchTask(folder, this, emitter));
            initThread.start();
        });

        source.subscribe((p) ->{
            this.addResult(p);
            if (p.getX().equals("endComputation") ){
                this.view.endComputation();
            }
        });
    }

    @Override
    public void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));
        Observable<Pair<String, Integer>> source = Observable.create(emitter -> {
            Thread initThread = new Thread( new FolderSearchTask(folder, this, emitter));
            initThread.start();
        });

        source.subscribe((p) ->{
            this.addResult(p);
        });

    }

    @Override
    public ComputedFileImpl getResult() {
        return this.model.getResult();
    }

    @Override
    public synchronized void addResult(Pair<String, Integer> result) throws InterruptedException {
        this.model.getResult().add(result);
        this.view.resultsUpdated();

    }

    @Override
    public void stop() {

    }


}
