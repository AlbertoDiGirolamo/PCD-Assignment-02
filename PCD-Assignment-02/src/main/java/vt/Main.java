package vt;


import vt.controller.Controller;
import vt.model.Model;
import vt.model.ModelImpl;
import vt.view.ConsoleView;
import vt.view.GuiView;
import vt.view.View;

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