package vertx.controller;

import io.vertx.core.Vertx;
import vertx.SourceAnalyser;
import vertx.model.Folder;
import vertx.model.FolderSearchAgent;
import vertx.model.Model;
import vertx.utils.ComputedFileImpl;
import vertx.utils.Pair;
import vertx.view.View;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class Controller implements SourceAnalyser {
    private final Model model;
    private final View view;
    private Vertx vertx;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);

    }

    @Override
    public void getReport(String path, int topN, int maxL, int numIntervals) throws IOException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));
        vertx = Vertx.vertx();

        FolderSearchAgent folderSearchAgent = new FolderSearchAgent(folder, this, vertx);
        vertx.deployVerticle(folderSearchAgent).onComplete(res -> {
            this.view.endComputation();
        });

    }

    @Override
    public void analyzeSources(String path, int topN, int maxL, int numIntervals) throws IOException {
        this.model.setup(topN, maxL, numIntervals);
        Folder folder = Folder.fromDirectory(new File(path));
        vertx = Vertx.vertx();

        vertx.deployVerticle(new FolderSearchAgent(folder, this, vertx));
    }


    public ComputedFileImpl getResult() {
        return this.model.getResult();
    }

    public void addResult(Pair<String, Integer> result) throws InterruptedException {
        this.model.getResult().add(result);
        this.view.resultsUpdated();
    }

    public void endComputation() {
        this.view.endComputation();
    }

    public void stop() {
        vertx.eventBus().publish("stop", "");
    }

}
