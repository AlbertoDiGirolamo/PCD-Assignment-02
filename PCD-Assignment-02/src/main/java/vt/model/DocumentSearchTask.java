package vt.model;



import vt.controller.Controller;
import vt.utils.Pair;

public class DocumentSearchTask implements Runnable {
    
	private final Document document;
    private final Controller controller;

    public DocumentSearchTask(Document document, Controller controller) {
        super();
        this.document = document;
        this.controller = controller;
    }


    @Override
    public void run() {
        try {
            this.controller.addResult(new Pair<>(this.document.getPath(), this.document.countNumLines()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

