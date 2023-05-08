package vt.model;



import vt.controller.Controller;
import vt.utils.Pair;

import java.util.concurrent.RecursiveTask;

public class DocumentSearchVT implements Runnable {
    
	private final Document document;
    private final Controller controller;

    public DocumentSearchVT(Document document, Controller controller) {
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

