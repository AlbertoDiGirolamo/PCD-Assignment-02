package executors.view;


import executors.controller.Controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface View {
    void setController(Controller controller);

    void resultsUpdated() throws InterruptedException;

    void endComputation();

    void startConsole() throws IOException, ExecutionException, InterruptedException;
}
