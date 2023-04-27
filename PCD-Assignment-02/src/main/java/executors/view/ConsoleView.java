package executors.view;

import executors.controller.Controller;
import executors.utils.ComputedFileImpl;
import executors.utils.Pair;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConsoleView implements View{
    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void endComputation() {
        for(Pair<String, Integer> file : controller.getResult().getComputedFiles()) {
            System.out.println(file.getX()+": "+file.getY());
        }
    }

    @Override
    public void resultsUpdated() throws InterruptedException {
    }
}
