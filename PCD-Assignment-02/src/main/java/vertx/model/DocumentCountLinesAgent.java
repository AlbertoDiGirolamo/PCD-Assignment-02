package vertx.model;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import vertx.controller.Controller;
import vertx.utils.Pair;

public class DocumentCountLinesAgent extends AbstractVerticle {
    private Controller controller;
    private Document document;

    public DocumentCountLinesAgent(Document document, Controller controller){
        this.controller = controller;
        this.document = document;
    }
    public void start(Promise<Void> startPromise) throws InterruptedException {
        controller.addResult(new Pair<>(document.getPath(), document.countNumLines()));

        EventBus eb = this.getVertx().eventBus();
        eb.publish("send-message", "");

        startPromise.complete();
    }

}
