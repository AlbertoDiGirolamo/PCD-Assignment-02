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

    int numAgent =0;

    public FolderSearchAgent(Folder folder, Controller controller, Vertx vertx) {
        this.folder = folder;
        this.controller = controller;
        this.vertx = vertx;
    }

    public void start(Promise<Void> startPromise) {

        numAgent += folder.getSubFolders().size();
        numAgent += folder.getDocuments().size();

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
            DocumentCountLinesAgent documentCountLinesAgent = new DocumentCountLinesAgent(document, controller);
            vertx.deployVerticle(documentCountLinesAgent).onComplete(event ->{
                numAgent--;
                if(numAgent == 0){
                    startPromise.complete();
                }
            });
        }

    }

}
