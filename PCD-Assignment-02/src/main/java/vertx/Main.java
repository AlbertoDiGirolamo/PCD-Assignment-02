package vertx;



import vertx.controller.Controller;
import vertx.model.Model;
import vertx.model.ModelImpl;
import vertx.view.ConsoleView;
import vertx.view.GuiView;
import vertx.view.View;

import java.io.IOException;

public class Main {
    static Controller controller;
    public static void main(String[] args) throws IOException, InterruptedException {
        final Model model = new ModelImpl();
        final View view = new ConsoleView();
        controller = new Controller(model, view);

        view.startConsole();
    }

}