package executors.model;

import executors.utils.Pair;
import executors.utils.SynchronizedList;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask extends RecursiveTask<SynchronizedList> {
    private final Folder folder;

    public FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
    }
    
    @Override
    protected SynchronizedList compute() {
        List<RecursiveTask<Pair<String, Integer>>> DocumentsForks = new LinkedList<>();
        List<RecursiveTask<SynchronizedList>> SubfolderForks = new LinkedList<>();

        SynchronizedList results = new SynchronizedList();

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder);
            SubfolderForks.add(task);
            task.fork();
        }
        
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document);
            DocumentsForks.add(task);
            task.fork();
        }

        for (RecursiveTask<Pair<String, Integer>> task : DocumentsForks) {
            results.add(task.join());
        }

        for (RecursiveTask<SynchronizedList> task : SubfolderForks) {
            results.addAll(task.join());
        }

        return results;
    }
}
    