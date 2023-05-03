package vertx;



import vertx.controller.Controller;
import vertx.controller.ControllerImpl;
import vertx.model.Model;
import vertx.model.ModelImpl;
import vertx.view.ConsoleView;
import vertx.view.GuiView;
import vertx.view.View;

import java.io.IOException;

public class Main {

    public static final int NUMBER_OF_WORKERS = Runtime.getRuntime().availableProcessors();
    static Controller controller;
    public static void main(String[] args) throws IOException, InterruptedException {
        final Model model = new ModelImpl();
        final View view = new GuiView();
        controller = new ControllerImpl(model, view);

        //setupConsole();
    }

    private static void setupConsole() throws IOException, InterruptedException {

        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci il path: ");
        String path = bufferedReader.readLine();
        System.out.println("Inserisci il numero di file da visualizzare nella classifica: ");
        int limit = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero max di linee: ");
        int maxL = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero di intervalli: ");
        int numIntervals = Integer.parseInt(bufferedReader.readLine());
        */

        controller.getReport("./file", 3, 40, 3);
    }
}