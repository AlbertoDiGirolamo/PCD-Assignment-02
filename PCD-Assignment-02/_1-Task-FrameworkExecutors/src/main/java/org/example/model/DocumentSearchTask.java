package org.example.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<List<Document>> {
    
	private final Document document;
    private final String searchedWord;
    private final LinesCounter lc;
    
    public DocumentSearchTask(LinesCounter lc, Document document, String searchedWord) {
        super();
        this.document = document;
        this.searchedWord = searchedWord;
        this.lc = lc;
    }
    
    @Override
    protected List<Document> compute() {
        try {
            document.countNumLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        return documents;
    }
}

