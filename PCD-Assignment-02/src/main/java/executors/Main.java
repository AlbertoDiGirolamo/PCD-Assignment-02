package executors;



import executors.controller.Controller;
import executors.model.Model;
import executors.model.ModelImpl;
import executors.view.ConsoleView;
import executors.view.GuiView;
import executors.view.View;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    static Controller controller;
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        final Model model = new ModelImpl();
        final View view = new GuiView();
        controller = new Controller(model, view);

        //view.startConsole();
    }

}