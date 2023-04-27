package executors.model;

import executors.utils.ComputedFileImpl;
import executors.utils.Pair;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<Pair<String, Integer>> {
    
	private final Document document;

    public DocumentSearchTask(Document document) {
        super();
        this.document = document;
    }
    
    @Override
    protected Pair<String, Integer> compute() {
        return new Pair<>(document.getPath(), document.countNumLines());
    }
}

