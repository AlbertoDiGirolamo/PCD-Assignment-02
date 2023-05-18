package vt.model;

import vt.controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FolderSearchTask implements Runnable{
    private final Folder folder;
    private Controller controller;


    public FolderSearchTask(Folder folder, Controller controller) {
        super();
        this.folder = folder;
        this.controller = controller;
    }


    @Override
    public void run() {

        List<Thread> threads = new ArrayList<>();

        for (Folder subFolder : folder.getSubFolders()) {
            Thread subFolderThread = Thread.ofVirtual().unstarted(new FolderSearchTask(subFolder, controller));
            subFolderThread.start();
            threads.add(subFolderThread);

        }

        for (Document document : folder.getDocuments()) {
            Thread documentThread = Thread.ofVirtual().unstarted(new DocumentSearchTask(document, controller));
            documentThread.start();
            threads.add(documentThread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
    