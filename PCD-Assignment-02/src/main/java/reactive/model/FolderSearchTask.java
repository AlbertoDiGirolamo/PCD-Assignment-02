package reactive.model;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.ObservableEmitter;
import reactive.controller.Controller;
import reactive.controller.ControllerImpl;
import reactive.utils.Pair;
import reactive.utils.SynchronizedList;
import vt.model.FolderSearchVT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask implements Runnable{
    private final Folder folder;
    private Controller controller;
    private ObservableEmitter<Pair<String, Integer>> emitter;

    public FolderSearchTask(Folder folder, Controller controller, ObservableEmitter<Pair<String, Integer>> emitter) {
        super();
        this.folder = folder;
        this.controller = controller;
        this.emitter = emitter;


    }

    @Override
    public void run() {
        List<Thread> threads = new ArrayList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            Thread subFolderThread = Thread.ofVirtual().unstarted(new FolderSearchTask(subFolder, controller,emitter));
            subFolderThread.start();
            threads.add(subFolderThread);
        }

        for (Document document : folder.getDocuments()) {
            new DocumentSearchTask(document, emitter);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        emitter.onNext(new Pair<>("endComputation", 0));
    }
}
    