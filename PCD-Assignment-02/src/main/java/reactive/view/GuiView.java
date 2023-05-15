package reactive.view;


import reactive.controller.Controller;
import reactive.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class GuiView implements View {
    private Controller controller;
    private JList<Pair<String, Integer>> rankingList = new JList<>();
    private final JList<String> distributionList = new JList<>();
    private final JFrame frame = new JFrame();
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    final JTextField txtNFiles;
    final JPanel resultsPanel = new JPanel();

    public GuiView() {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.frame.setTitle("Source Tracker");
        this.frame.setSize(1000, 800);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        final JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        final JLabel lblDirectory = new JLabel("Start directory: ");
        final JTextField txtDirectory = new JTextField(20);
        txtDirectory.setMinimumSize(txtDirectory.getPreferredSize());
        txtDirectory.setBounds(50, 80, 200, 30);

        final JLabel lblNFiles = new JLabel("Number of files: ");
        txtNFiles = new JTextField(3);
        txtNFiles.setBounds(50, 140, 200, 30);
        txtDirectory.setMinimumSize(txtDirectory.getPreferredSize());

        final JLabel lblIntervals = new JLabel("Number of intervals: ");
        final JTextField txtIntervals = new JTextField(3);
        txtDirectory.setMinimumSize(txtDirectory.getPreferredSize());

        final JLabel lblLastInterval = new JLabel("Last interval: ");
        final JTextField txtLastInterval = new JTextField(3);
        txtDirectory.setMinimumSize(txtDirectory.getPreferredSize());


        btnStop.setEnabled(false);

        btnStop.addActionListener(e -> {
            this.controller.stop();
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        });

        btnStart.addActionListener(e -> {
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);

            this.controller.analyzeSources(txtDirectory.getText(), Integer.parseInt(txtNFiles.getText()), Integer.parseInt(txtLastInterval.getText()), Integer.parseInt(txtIntervals.getText())).subscribe((results) -> {
                DefaultListModel<Pair<String, Integer>> rankingModel = new DefaultListModel<>();
                rankingModel.addAll(results.getRanking());


                DefaultListModel<String> intervalsModel = new DefaultListModel<>();
                intervalsModel.addAll(results.getFilesInRange().entrySet().stream().map(f->f.getKey().getX()+"-"+f.getKey().getY()+": "+f.getValue()).collect(Collectors.toList()));

                SwingUtilities.invokeLater(() -> rankingList.setModel(rankingModel));
                SwingUtilities.invokeLater(() -> distributionList.setModel(intervalsModel));

            });

            this.rankingList.setModel(new DefaultListModel<>());

            this.rankingList.setSize(100, 50);
            this.rankingList.setAutoscrolls(true);

            resultsPanel.add(rankingList);
        });

        this.distributionList.setSize(100, 50);

        inputPanel.add(lblDirectory);
        inputPanel.add(txtDirectory);
        inputPanel.add(lblNFiles);
        inputPanel.add(txtNFiles);
        inputPanel.add(lblIntervals);
        inputPanel.add(txtIntervals);
        inputPanel.add(lblLastInterval);
        inputPanel.add(txtLastInterval);

        controlPanel.add(btnStart);
        controlPanel.add(btnStop);

        resultsPanel.add(distributionList);

        mainPanel.add(inputPanel);
        mainPanel.add(controlPanel);
        mainPanel.add(resultsPanel);

        this.frame.setContentPane(mainPanel);
        this.frame.setVisible(true);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void startConsole() throws IOException, ExecutionException, InterruptedException {

    }

}
