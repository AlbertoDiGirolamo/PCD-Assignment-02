package executors.model;

import executors.controller.Controller;
import executors.utils.Pair;
import executors.utils.SynchronizedList;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask extends RecursiveTask<Future<SynchronizedList>> {
    private final Folder folder;
    private Controller controller;

    public FolderSearchTask(Folder folder, Controller controller) {
        super();
        this.folder = folder;
        this.controller = controller;
    }
    
    @Override
    protected Future<SynchronizedList> compute() {
        List<RecursiveTask<Pair<String, Integer>>> DocumentsForks = new LinkedList<>();
        List<RecursiveTask<Future<SynchronizedList>>> SubfolderForks = new LinkedList<>();

        Future<SynchronizedList> results = new SynchronizedList();

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
            try {
                results.get().add(file);
                controller.addResult(file);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }

        for (RecursiveTask<Future<SynchronizedList>> task : SubfolderForks) {
            try {
                results.get().addAll(task.join().get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        //this.cancel(false);
        return results;
    }
}
    