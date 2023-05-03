package executors.model;

import executors.controller.Controller;
import executors.utils.Pair;
import executors.utils.SynchronizedList;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask extends RecursiveTask<SynchronizedList> {
    private final Folder folder;
    private Controller controller;

    public FolderSearchTask(Folder folder, Controller controller) {
        super();
        this.folder = folder;
        this.controller = controller;
    }
    
    @Override
    protected SynchronizedList compute() {
        List<RecursiveTask<Pair<String, Integer>>> DocumentsForks = new LinkedList<>();
        List<RecursiveTask<SynchronizedList>> SubfolderForks = new LinkedList<>();

        SynchronizedList results = new SynchronizedList();

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder, controller);
            SubfolderForks.add(task);
            task.fork();
        }
        
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document);
            DocumentsForks.add(task);
            task.fork();
        }

        for (RecursiveTask<Pair<String, Integer>> task : DocumentsForks) {
            Pair<String, Integer> file = task.join();
            results.add(file);
            try {
                controller.addResult(file);
                //Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (RecursiveTask<SynchronizedList> task : SubfolderForks) {
            results.addAll(task.join());
        }

        //this.cancel(false);
        return results;
    }
}
    