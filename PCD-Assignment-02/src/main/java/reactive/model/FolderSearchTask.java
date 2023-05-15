package reactive.model;

import io.reactivex.rxjava3.core.ObservableEmitter;
import reactive.controller.Controller;
import reactive.utils.Pair;

import java.util.ArrayList;
import java.util.List;

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
    