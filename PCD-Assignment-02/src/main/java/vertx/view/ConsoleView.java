package vertx.view;

import vertx.controller.Controller;
import vertx.utils.Pair;

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
    public void resultsUpdated() throws InterruptedException {
    }
}
