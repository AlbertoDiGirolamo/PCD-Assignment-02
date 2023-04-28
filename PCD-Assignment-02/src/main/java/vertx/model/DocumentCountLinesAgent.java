package vertx.model;

import io.vertx.core.AbstractVerticle;
import vertx.controller.Controller;
import vertx.utils.Pair;

public class DocumentCountLinesAgent extends AbstractVerticle {
    private Controller controller;
    private Document document;

    public DocumentCountLinesAgent(Document document, Controller controller){
        this.controller = controller;
        this.document = document;
    }
    public void start() {
        controller.addResult(new Pair<>(document.getPath(), document.countNumLines()));

    }
    private void log(String msg) {
        System.out.println("[DocumentCountLinesAgent]["+Thread.currentThread()+"] " + msg);
    }
}
