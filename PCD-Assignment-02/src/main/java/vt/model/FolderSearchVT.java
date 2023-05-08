package vt.model;

import vt.controller.Controller;
import vt.utils.Pair;
import vt.utils.SynchronizedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class FolderSearchVT implements Runnable{
    private final Folder folder;
    private Controller controller;

    public FolderSearchVT(Folder folder, Controller controller) {
        super();
        this.folder = folder;
        this.controller = controller;
    }


    @Override
    public void run() {

        List<Thread> threads = new ArrayList<>();

        for (Folder subFolder : folder.getSubFolders()) {
            Thread subFolderThread = Thread.ofVirtual().unstarted(new FolderSearchVT(subFolder, controller));
            subFolderThread.start();
            threads.add(subFolderThread);

        }

        for (Document document : folder.getDocuments()) {
            Thread documentThread = Thread.ofVirtual().unstarted(new DocumentSearchVT(document, controller));
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
    