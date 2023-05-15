package vertx.view;


import vertx.controller.Controller;

import java.io.IOException;

public interface View {
    void setController(Controller controller);

    void resultsUpdated() throws InterruptedException;

    void endComputation();

    void startConsole() throws IOException;
}
