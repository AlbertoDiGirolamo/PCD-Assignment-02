package reactive;


import reactive.controller.Controller;
import reactive.controller.ControllerImpl;
import reactive.model.Model;
import reactive.model.ModelImpl;
import reactive.utils.ComputedFileImpl;
import reactive.utils.Pair;
import reactive.view.ConsoleView;
import reactive.view.GuiView;
import reactive.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main {

    static Controller controller;
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        final Model model = new ModelImpl();
        final View view = new ConsoleView();
        controller = new ControllerImpl(model, view);

        setupConsole();
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
        int numIntervals = Integer.parseInt(bufferedReader.readLine());

        controller.getReport(path, limit, maxL, numIntervals);*/

        //controller.getReport("./fileExample", 10, 1000, 10);
        controller.getReport("./fileExample", 10, 1000, 10).subscribe((results) -> {
            System.out.println("Ranking: ");

            for(Pair<String, Integer> p : results.getRanking()) {
                System.out.println(p.getX()+": "+p.getY());
            }

            System.out.println("Intervals: ");

            for(Map.Entry<Pair<Integer, Integer>, Integer> p : results.getFilesInRange().entrySet()) {
                System.out.println(p.getKey().getX()+"-"+p.getKey().getY()+": "+p.getValue());
            }
        });


    }
}