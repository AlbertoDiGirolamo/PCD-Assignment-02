package vertx.view;

import vertx.controller.Controller;
import vertx.utils.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConsoleView implements View {
    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void endComputation() {
        System.out.println("Ranking: ");

        List<Pair<String, Integer>> rankingList = new LinkedList<>(this.controller.getResult().getRanking());
        for(Pair<String, Integer> p : rankingList) {
            System.out.println(p.getX()+": "+p.getY());
        }

        System.out.println("Intervals: ");
        Map<Pair<Integer, Integer>, Integer> filesInRange = this.controller.getResult().getFilesInRange();
        for(Map.Entry<Pair<Integer, Integer>, Integer> p : filesInRange.entrySet()) {
            System.out.println(p.getKey().getX()+"-"+p.getKey().getY()+": "+p.getValue());
        }

    }

    @Override
    public void startConsole() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci il path: ");
        String path = bufferedReader.readLine();
        System.out.println("Inserisci il numero di file da visualizzare nella classifica: ");
        int limit = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero max di linee: ");
        int maxL = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero di intervalli: ");
        int numIntervals = Integer.parseInt(bufferedReader.readLine());

        this.controller.getReport(path, limit, maxL, numIntervals);
    }

    @Override
    public void resultsUpdated() throws InterruptedException {
    }
}
