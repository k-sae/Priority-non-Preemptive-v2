package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{
    private static Stage stage;
    public static void main(String[] args) {

        launch(args);


        /*
        ArrayList <Process>  processes = new ArrayList<>();
        Process process1 =new Process();
        Process process2 =new Process();
        Process process3 =new Process();
        Process process4 =new Process();
        Process process5 =new Process();
        process1.setArrivalTime(0);
        process1.setNumber(1);
        process1.setBurstTime(3);
        process1.setPriority(5);
        processes.add(process1);
        process2.setArrivalTime(1);
        process2.setNumber(2);
        process2.setBurstTime(7);
        process2.setPriority(3);
        processes.add(process2);
        process3.setArrivalTime(4);
        process3.setNumber(3);
        process3.setBurstTime(2);
        process3.setPriority(4);
        processes.add(process3);
        process4.setArrivalTime(2);
        process4.setNumber(4);
        process4.setBurstTime(3);
        process4.setPriority(3);
        processes.add(process4);
        process5.setArrivalTime(6 );
        process5.setNumber(5);
        process5.setBurstTime(4);
        process5.setPriority(1);
        processes.add(process5);
        Results results =new Results(processes);
        ArrayList<ProcessResult> processResults =new ArrayList<>();
        processResults =results.getResults();
        int l= processResults.size();
        for (int i=0;i<l;i++){
            System.out.println(i + "- "+ processResults.get(i).getNumber());
            System.out.println(i + "- "+ processResults.get(i).getStartTime());
            System.out.println(i + "- "+ processResults.get(i).getEndTime());
            System.out.println(i + "- "+ processResults.get(i).getWaitingTime());
            System.out.println(i + "- "+ processResults.get(i).getTurnaroundTime());
        }
        System.out.println(results.getAverageWaiting());
        System.out.println(results.getAverageTurnaround());
        */
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Priority non-preemptive");
        /** ADD THE APPLICATION ICON */
        //primaryStage.getIcons().add(new Image("..."));
        //primaryStage.setResizable(false);
        RegisterPage registerPage = new RegisterPage();
        ScrollPane scrollPane = new ScrollPane(registerPage);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        //stage.setResizable(false);
        stage.setScene(new Scene(scrollPane,960,600));
        stage.show();
    }
    public static void navigateTo (Parent node){
        stage.setScene(new Scene(node,960,600));
    }
}
