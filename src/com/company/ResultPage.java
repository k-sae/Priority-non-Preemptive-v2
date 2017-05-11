package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
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
    private TableView <ProcessResult> table;
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
        responseTimeColumn.setMinWidth(150);
        responseTimeColumn.setCellValueFactory(new PropertyValueFactory<>("responseTime"));

        ObservableList<ProcessResult> p = FXCollections.observableArrayList(processResults);
        table = new TableView<>();
        table.setMaxWidth(335);
        table.setMaxHeight(200);
        table.setItems(p);
        table.getColumns().addAll(processNumberColumn, waitingTimeColumn, turnaroundTimeColumn);

        getChildren().add(table);

/*
        int size = processResults.size();
        for(int i = 0; i< size; i++){
            HBox record = new HBox(20);
            Label processNumber = new Label(""+processResults.get(i).getNumber());
            Label processWatingTime = new Label(""+processResults.get(i).getWaitingTime());
            Label processTurnaroundTime = new Label(""+processResults.get(i).getTurnaroundTime());
            Label processResponseTime = new Label();

            record.getChildren().addAll(processNumber, processWatingTime, processTurnaroundTime, processResponseTime);
            getChildren().add(record);
        }*/
        Label aWaiting = new Label("Average Waiting time\t: " + results.getAverageWaiting());
        Label aResponse = new Label("Average Respond time\t: " + results.getAverageResponse());
        Label aTurnAround = new Label("Average Turn around\t: " + results.getAverageTurnaround());
        getChildren().add(aWaiting);
        getChildren().add(aResponse);
        getChildren().add(aTurnAround);
        restartBtn = new Button("Restart");
        getChildren().add(restartBtn);
        restartBtn.setOnAction(event -> Main.navigateTo(new RegisterPage()));
    }
    // update its performance later -_-
    //may support resizing although

    private void drawChart(){
        HBox chart = new HBox(1);
        for (ProcessResult processResult: processResults
             ) {
            pSum += processResult.getEndTime() - processResult.getStartTime();
        }
        renderCharts(chart, chart.getWidth());
        chart.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                chart.getChildren().clear();
                renderCharts(chart, newValue);
            }
        });
        getChildren().add(chart);
    }
    private void renderCharts(HBox chart, Number width)
    {
        for(int i=0; i< processResults.size(); i++){
            Label process = new Label("P" + processResults.get(i).getNumber());
            float pwidth = (((float)(processResults.get(i).getEndTime() - processResults.get(i).getStartTime())/(float)(pSum)) * width.floatValue());
            process.setMinWidth(pwidth - 1);
            process.setMinHeight(50);
            process.setAlignment(Pos.CENTER);
            process.setStyle("-fx-background-color: #ff0000");
            Label processStartTime = new Label(""+processResults.get(i).getStartTime());
            chart.getChildren().add(new VBox(process,processStartTime));
        }
    }
}
