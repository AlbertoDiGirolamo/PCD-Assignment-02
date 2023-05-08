package vertx.controller;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import vertx.SourceAnalyser;
import vertx.model.Folder;
import vertx.model.FolderSearchAgent;
import vertx.model.Model;
import vertx.utils.ComputedFileImpl;
import vertx.utils.Pair;
import vertx.utils.SynchronizedList;
import vertx.view.View;

import java.io.File;
import java.io.IOException;

public class ControllerImpl implements Controller, SourceAnalyser {
    private final Model model;
    private final View view;

    SynchronizedList results = new SynchronizedList();

    public ControllerImpl(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void getReport(String path, int topN, int maxL, int numIntervals) throws IOException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));

        Vertx vertx = Vertx.vertx();

        FolderSearchAgent folderSearchAgent = new FolderSearchAgent(folder, this, vertx);

        vertx.deployVerticle(folderSearchAgent);

        EventBus eb = vertx.eventBus();
        eb.consumer("my-topic", message -> {
            this.view.endComputation();
        });

    }

    @Override
    public void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new FolderSearchAgent(folder, this, vertx), res -> this.view.endComputation());
    }

    @Override
    public ComputedFileImpl getResult() {
        return this.model.getResult();
    }

    public void addResult(Pair<String, Integer> result) throws InterruptedException {
        this.model.getResult().add(result);
        this.view.resultsUpdated();
    }
    @Override
    public void endComputation() {
        this.view.endComputation();
    }

    @Override
    public void stop() {
    }

    @Override
    public void start(int numberOfWorkers, String path, int topN, int maxL, int numIntervals) {
        this.model.setup(topN, maxL, numIntervals);

    }

}
