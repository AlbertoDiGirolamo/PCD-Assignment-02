package reactive.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import reactive.SourceAnalyser;
import reactive.model.Folder;
import reactive.model.Model;
import reactive.utils.ComputedFileImpl;
import reactive.utils.Pair;
import reactive.view.View;

import java.io.File;
import java.time.LocalTime;

public class Controller implements SourceAnalyser {
    private final Model model;
    private final View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public Single<ComputedFileImpl> getReport(String path, int topN, int maxL, int numIntervals){
        ComputedFileImpl emptyResult = new ComputedFileImpl(topN, maxL, numIntervals);

        return this.analyzeFolder(path)
                .sequential()
                .reduce(emptyResult, (result, af) -> result.accumulate(af));
    }

    @Override
    public Flowable<ComputedFileImpl> analyzeSources(String path, int topN, int maxL, int numIntervals){
        this.model.resetStop();
        ComputedFileImpl result = new ComputedFileImpl(topN, maxL, numIntervals);

        return this.analyzeFolder(path)
                .sequential()
                .map(af -> result.accumulate(af));
    }

    private ParallelFlowable<Pair<String, Integer>> analyzeFolder(String folder){
        return Flowable.just(folder)
                .map(p -> Folder.fromDirectory(new File(p)))
                .flatMap(f -> this.getSubFolders(f))
                .subscribeOn(Schedulers.io())
                .parallel()
                .runOn(Schedulers.computation())
                .flatMap(f -> Flowable.fromIterable(f.getDocuments()))
                .map(d -> new Pair<>(d.getPath(), d.countNumLines()));
    }

    private Flowable<Folder> getSubFolders(Folder folder){
        return Flowable
                .fromIterable(folder.getSubFolders())
                .skipWhile(af -> this.model.getStop().get())
                .flatMap(f -> this.getSubFolders(f))
                .concatWith(Flowable.just(folder));
    }

    public void stop() {
        this.model.getStop().set(true);
    }



}
