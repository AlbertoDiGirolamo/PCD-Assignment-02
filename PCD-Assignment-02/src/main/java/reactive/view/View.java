package reactive.view;


import reactive.controller.Controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface View {
    void setController(Controller controller);

    void startConsole() throws IOException, ExecutionException, InterruptedException;
}
