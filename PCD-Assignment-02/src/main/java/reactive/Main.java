package reactive;


import reactive.controller.Controller;
import reactive.model.Model;
import reactive.model.ModelImpl;
import reactive.view.ConsoleView;
import reactive.view.View;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

    static Controller controller;
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        final Model model = new ModelImpl();
        final View view = new ConsoleView();
        controller = new Controller(model, view);

        view.startConsole();

    }


}