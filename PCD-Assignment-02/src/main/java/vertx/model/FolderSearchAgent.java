package vertx.model;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import vertx.controller.Controller;

public class FolderSearchAgent extends AbstractVerticle {
    private final Folder folder;
    private Controller controller;
    Vertx  vertx;
    int numAgent = 0;

    public FolderSearchAgent(Folder folder, Controller controller, Vertx vertx) {
        this.folder = folder;
        this.controller = controller;
        this.vertx = vertx;
    }

    public void start(Promise<Void> startPromise) {

        numAgent += folder.getSubFolders().size();
        numAgent += folder.getDocuments().size();

        vertx.eventBus().consumer("stop", message -> {
            vertx.close();
        });

        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchAgent folderSearchAgent = new FolderSearchAgent(subFolder, controller, vertx);
            vertx.deployVerticle(folderSearchAgent).onComplete(event ->{
                numAgent--;
                if(numAgent == 0){
                    startPromise.complete();
                }
            });
        }

        for (Document document : folder.getDocuments()) {
            DocumentSearchAgent documentSearchAgent = new DocumentSearchAgent(document, controller);
            vertx.deployVerticle(documentSearchAgent).onComplete(event ->{
                numAgent--;
                if(numAgent == 0){
                    startPromise.complete();
                }
            });
        }

    }

}
