package vertx.model;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import vertx.controller.Controller;

public class FolderSearchAgent extends AbstractVerticle {
    private final Folder folder;
    private Controller controller;
    Vertx  vertx;

    public FolderSearchAgent(Folder folder, Controller controller, Vertx vertx) {
        this.folder = folder;
        this.controller = controller;
        this.vertx = vertx;
    }

    public void start(Promise<Void> startPromise) {
        log("started.");

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchAgent folderSearchAgent = new FolderSearchAgent(subFolder, controller, vertx);

            vertx.deployVerticle(folderSearchAgent);
        }

        for (Document document : folder.getDocuments()) {
            DocumentCountLinesAgent documentCountLinesAgent = new DocumentCountLinesAgent(document, controller);

            vertx.deployVerticle(documentCountLinesAgent);
        }

        startPromise.complete();

    }

    private void log(String msg) {
        System.out.println("[FolderSearchAgent]["+Thread.currentThread()+"] " + msg);
    }
}
