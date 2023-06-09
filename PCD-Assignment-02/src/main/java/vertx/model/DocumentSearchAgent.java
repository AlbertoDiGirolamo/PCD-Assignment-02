package vertx.model;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import vertx.controller.Controller;
import vertx.utils.Pair;

public class DocumentSearchAgent extends AbstractVerticle {
    private Controller controller;
    private Document document;

    public DocumentSearchAgent(Document document, Controller controller){
        this.controller = controller;
        this.document = document;
    }
    public void start(Promise<Void> startPromise) throws InterruptedException {
        controller.addResult(new Pair<>(document.getPath(), document.countNumLines()));
        startPromise.complete();
    }

}
