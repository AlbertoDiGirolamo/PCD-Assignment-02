package vertx.view;


import vertx.controller.Controller;

public interface View {
    void setController(Controller controller);

    void resultsUpdated() throws InterruptedException;

    void endComputation();
}
