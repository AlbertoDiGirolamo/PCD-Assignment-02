package reactive.view;


import reactive.controller.Controller;

public interface View {
    void setController(Controller controller);

    void resultsUpdated() throws InterruptedException;

    void endComputation();
}
