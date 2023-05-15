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

public class ControllerImpl implements Controller, SourceAnalyser {
    private final Model model;
    private final View view;

    public ControllerImpl(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public Single<ComputedFileImpl> getReport(String path, int topN, int maxL, int numIntervals){
        //this.model.setup(topN, maxL, numIntervals);

        ComputedFileImpl emptyResult = new ComputedFileImpl(topN, maxL, numIntervals);

        return this.analyzeFolder(path)
                .sequential() //Merge all the parallel flowable in a single one
                .reduce(emptyResult, (result, af) -> result.accumulate(af));
    }

    @Override
    public Flowable<ComputedFileImpl> analyzeSources(String path, int topN, int maxL, int numIntervals){
       // this.model.resetStopExecution();
        ComputedFileImpl result = new ComputedFileImpl(topN, maxL, numIntervals);

        return this.analyzeFolder(path)
                .sequential()//Merge all the parallel flowable in a single one
                .map(af -> result.accumulate(af));
    }

    private ParallelFlowable<Pair<String, Integer>> analyzeFolder(String folder){
        return Flowable.just(folder)
                .map(p -> Folder.fromDirectory(new File(p)))
                .flatMap(f -> this.getSubFolders(f))
                .subscribeOn(Schedulers.io())  //Execute the upstream operators on another Thread
                .parallel()
                .runOn(Schedulers.computation()) //Execute each parallel computation on a different Thread
                .flatMap(f -> Flowable.fromIterable(f.getDocuments()))
                .map(d -> new Pair<>(d.getPath(), d.countNumLines()));
    }

    private Flowable<Folder> getSubFolders(Folder folder){
        return Flowable
                .fromIterable(folder.getSubFolders())
                //.skipWhile(af -> this.model.getStopExecution().get())
                .flatMap(f -> this.getSubFolders(f))
                .concatWith(Flowable.just(folder));
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
