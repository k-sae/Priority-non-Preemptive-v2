package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

/**
 * Created by billy on 4/9/17.
 */
public class ResultPage extends VBox {
    private ArrayList <Process> processes;
    private ArrayList<ProcessResult> processResults;
    private Button restartBtn;
    private Results results;
    private int pSum;
    public ResultPage(ArrayList <Process> processes){
        this.processes = processes;
        results = new Results(processes);
        processResults = new ArrayList<>();
        processResults = results.getResults();
        setLayout();
    }

    private void setLayout(){
        setPadding(new Insets(30));
        setSpacing(30);
        Label title = new Label("Priority non-preemptive Scheduling");
        title.setFont(Font.font(26));
        getChildren().add(title);

        drawChart();
        ArrayList<ProcessResult> filteredResults = new ArrayList<>();
        for (ProcessResult processResult: processResults) {
            if (processResult.getNumber() != 0)
            {
                filteredResults.add(processResult);
            }
        }

        TableColumn<ProcessResult, Integer> processNumberColumn = new TableColumn<>("No.");
        processNumberColumn.setMinWidth(50);
        processNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<ProcessResult, Integer> waitingTimeColumn = new TableColumn<>("Waiting Time");
        waitingTimeColumn.setMinWidth(130);
        waitingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));

        TableColumn<ProcessResult, Integer> turnaroundTimeColumn = new TableColumn<>("Turnaround Time");
        turnaroundTimeColumn.setMinWidth(150);
        turnaroundTimeColumn.setCellValueFactory(new PropertyValueFactory<>("turnaroundTime"));

        TableColumn<ProcessResult, Integer> responseTimeColumn = new TableColumn<>("Response Time");
        responseTimeColumn.setMinWidth(140);
        responseTimeColumn.setCellValueFactory(new PropertyValueFactory<>("responseTime"));

        ObservableList<ProcessResult> p = FXCollections.observableArrayList(filteredResults);
        TableView <ProcessResult> table = new TableView<>();
        table.setMaxWidth(475);
        table.setMaxHeight(200);
        table.setItems(p);
        table.setStyle("-fx-focus-color: transparent;-fx-background-insets: 0 0 -1 0, 0, 1, 2;");
        table.getColumns().addAll(processNumberColumn, waitingTimeColumn, turnaroundTimeColumn, responseTimeColumn);

        TableColumn<Results, String> avgColumn = new TableColumn<>();
        avgColumn.setMinWidth(50);
        avgColumn.setCellFactory(column -> {
            return new TableCell<Results, String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText("AVG");
                    }                }
            };
        });

        TableColumn<Results, Integer> avgwaitingTimeColumn = new TableColumn<>();
        avgwaitingTimeColumn.setMinWidth(130);
        avgwaitingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("averageWaiting"));

        TableColumn<Results, Integer> avgturnaroundTimeColumn = new TableColumn<>();
        avgturnaroundTimeColumn.setMinWidth(150);
        avgturnaroundTimeColumn.setCellValueFactory(new PropertyValueFactory<>("averageTurnaround"));

        TableColumn<Results, Integer> avgresponseTimeColumn = new TableColumn<>();
        avgresponseTimeColumn.setMinWidth(140);
        avgresponseTimeColumn.setCellValueFactory(new PropertyValueFactory<>("averageResponse"));

        ObservableList<Results> ap = FXCollections.observableArrayList(results);
        TableView <Results> atable = new TableView<>();
        atable.widthProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
            {
                //Don't show header
                Pane header = (Pane) atable.lookup("TableHeaderRow");
                if (header.isVisible()){
                    header.setMaxHeight(0);
                    header.setMinHeight(0);
                    header.setPrefHeight(0);
                    header.setVisible(false);
                }
            }
        });

        atable.setMaxWidth(475);
        atable.setMaxHeight(26);
        atable.setStyle("-fx-focus-color: transparent;-fx-background-insets: 0 0 -1 0, 0, 1, 2;");
        atable.setItems(ap);
        atable.getColumns().addAll(avgColumn, avgwaitingTimeColumn, avgturnaroundTimeColumn, avgresponseTimeColumn);
        getChildren().add(new VBox(table,atable));

        restartBtn = new Button("Restart");
        getChildren().add(restartBtn);
        restartBtn.setOnAction(event -> Main.navigateTo(new RegisterPage()));
    }

    private void drawChart(){
        HBox chart = new HBox(2);
        for (ProcessResult processResult: processResults) {
            pSum += processResult.getEndTime() - processResult.getStartTime();
        }
        renderCharts(chart, chart.getWidth());
        chart.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                for (Node node: chart.getChildren()) {
                    if (node instanceof  VBox)
                    {
                        ((ChartPortion)(((VBox) node).getChildren().get(0))).setWidthWithRatio(newValue.floatValue());
                    }
                }
            }
        });
        getChildren().add(chart);
    }
    private void renderCharts(HBox chart, Number width)
    {
        for(int i=0; i< processResults.size(); i++){
            float pwidth = (((float)(processResults.get(i).getEndTime() - processResults.get(i).getStartTime())/(float)(pSum)) );
            ChartPortion process = new ChartPortion("", pwidth);
            process.setWidthWithRatio(width.floatValue());
            process.setMinHeight(50);
            process.setAlignment(Pos.CENTER);

            Label processStartTime = new Label("" + processResults.get(i).getStartTime());
            if (processResults.get(i).getNumber() != 0) {
                process.setText("P" + processResults.get(i).getNumber());
                process.setStyle("-fx-background-color: #ff0000;-fx-border-color:black; -fx-border-width: 3; -fx-border-style: solid;");
            }

            chart.getChildren().add(new VBox(process, processStartTime));

            if (i == processResults.size() - 1) {
                process = new ChartPortion("", 0);
                process.setMinWidth(25);
                process.setMinHeight(50);
                Label processEndTime = new Label("" + processResults.get(i).getEndTime());
                chart.getChildren().add(new VBox(process, processEndTime));
            }
        }
    }
}
