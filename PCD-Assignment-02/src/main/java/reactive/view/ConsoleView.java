package reactive.view;

import reactive.controller.Controller;
import reactive.utils.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleView implements View {
    private Controller controller;
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }


    public void startConsole() throws IOException, ExecutionException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci il path: ");
        String path = bufferedReader.readLine();
        System.out.println("Inserisci il numero di file da visualizzare nella classifica: ");
        int limit = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero max di linee: ");
        int maxL = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Inserisci il numero di intervalli: ");
        int numIntervals = Integer.parseInt(bufferedReader.readLine());

        AtomicBoolean computationEnded = new AtomicBoolean();
        controller.getReport(path, limit, maxL, numIntervals).subscribe((results) -> {
            
            System.out.println("Ranking: ");

            for(Pair<String, Integer> p : results.getRanking()) {
                System.out.println(p.getX()+": "+p.getY());
            }

            System.out.println("Intervals: ");

            for(Map.Entry<Pair<Integer, Integer>, Integer> p : results.getFilesInRange().entrySet()) {
                System.out.println(p.getKey().getX()+"-"+p.getKey().getY()+": "+p.getValue());
            }
            computationEnded.set(true);
        });


        while(!computationEnded.get()){
            Thread.sleep(100);
        }
    }
}
