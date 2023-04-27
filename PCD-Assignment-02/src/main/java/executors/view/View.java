package executors.view;


import executors.controller.Controller;

public interface View {
    void setController(Controller controller);

    void resultsUpdated() throws InterruptedException;

    void endComputation();
}
