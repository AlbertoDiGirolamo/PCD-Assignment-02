package vt;


import vt.controller.Controller;
import vt.controller.ControllerImpl;
import vt.model.Model;
import vt.model.ModelImpl;
import vt.view.ConsoleView;
import vt.view.GuiView;
import vt.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    static Controller controller;
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        final Model model = new ModelImpl();
        final View view = new GuiView();
        controller = new ControllerImpl(model, view);

        //setupConsole();
    }

    private static void setupConsole() throws IOException, InterruptedException, ExecutionException {

        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci il path: ");
        String path = bufferedReader.readLine();
        System.out.println("Inserisci il numero di file da visualizzare nella classifica: ");
        int limit = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero max di linee: ");
        int maxL = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero di intervalli: ");
        int numIntervals = Integer.parseInt(bufferedReader.readLine());*/


        //controller.getReport(path, limit, maxL, numIntervals);
        controller.getReport("./fileExample", 10, 1000, 10).get();
        controller.endComputation();

    }
}