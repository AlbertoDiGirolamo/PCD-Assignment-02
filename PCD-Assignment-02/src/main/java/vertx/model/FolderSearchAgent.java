package vertx.model;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import vertx.controller.Controller;

public class FolderSearchAgent extends AbstractVerticle {
    private final Folder folder;
    private Controller controller;

    public FolderSearchAgent(Folder folder, Controller controller) {
        this.folder = folder;
        this.controller = controller;
    }

    public void start(Promise<Void> startPromise) {
        log("started.");

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchAgent folderSearchAgent = new FolderSearchAgent(subFolder, controller);

            Vertx  vertx = Vertx.vertx();
            vertx.deployVerticle(folderSearchAgent);
        }

        for (Document document : folder.getDocuments()) {
            DocumentCountLinesAgent documentCountLinesAgent = new DocumentCountLinesAgent(document, controller);

            Vertx  vertx = Vertx.vertx();
            vertx.deployVerticle(documentCountLinesAgent);
        }

        EventBus eb = this.getVertx().eventBus();
        eb.consumer("my-topic", message -> {
            log("new message: " + message.body());
        });
        log("Ready.");
        startPromise.complete();
    }

    private void log(String msg) {
        System.out.println("[FolderSearchAgent]["+Thread.currentThread()+"] " + msg);
    }
}
