package org.example.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask extends RecursiveTask<List<Document>> {
    private final Folder folder;
    private final String searchedWord;
    private final LinesCounter lc;
    
    public FolderSearchTask(LinesCounter lc, Folder folder, String searchedWord) {
        super();
        this.lc = lc;
        this.folder = folder;
        this.searchedWord = searchedWord;
    }
    
    @Override
    protected List<Document> compute() {
        long count = 0L;
        List<Document> documents = new ArrayList<>();
        List<RecursiveTask<List<Document>>> forks = new LinkedList<RecursiveTask<List<Document>>>();

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(lc, subFolder, searchedWord);
            forks.add(task);
            task.fork();
        }
        
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(lc, document, searchedWord);
            forks.add(task);
            task.fork();
        }
        
        for (RecursiveTask<List<Document>> task : forks) {
            documents.add(task.join().get(0));
        }

        return documents;
    }
}
    