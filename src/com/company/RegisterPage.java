package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by billy on 4/9/17.
 */
public class RegisterPage extends VBox {
    private Label processNoLBL;
    private TextField processNoTF;
    private Label errorLBL;
    private Button continueBtn;

    public RegisterPage(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.CENTER);
        setPadding(new Insets(30));
        setSpacing(30);

        processNoLBL = new Label("Enter the number of processes:");
        processNoLBL.setFont(Font.font(26));

        processNoTF = new TextField();
        processNoTF.setMaxWidth(50);
        processNoTF.setFont(Font.font(18));

        errorLBL = new Label("Error: please enter a valid number!");
        errorLBL.setFont(Font.font(18));
        errorLBL.setTextFill(Paint.valueOf("#ff0000"));
        errorLBL.setVisible(false);

        continueBtn = new Button("Continue");
        continueBtn.setOnAction(event -> {
            if(isValid(processNoTF.getText())) {
                errorLBL.setVisible(false);
                setOptions(Integer.parseInt(processNoTF.getText()));
            }else
                errorLBL.setVisible(true);
        });

        getChildren().addAll(processNoLBL, processNoTF, errorLBL, continueBtn);

    }

    private void setOptions(int processNo){

        getChildren().clear();

        //Label titles = new Label("No.\t    Arrival Time \tCPU Burst Time \t  Priority\t   ");
        //getChildren().add(titles);
        Label no = new Label("No");
        Label at = new Label("Arrival Time ");
        Label ct = new Label("CPU Burst Time");
        Label pt = new Label("Priority");

        TextField arrivaltimetf[] = new TextField[processNo];
        TextField cpubursttf[] = new TextField[processNo];
        TextField prioritytf[] = new TextField[processNo];

        for(int i = 0; i < processNo; i++){
            HBox record = new HBox(20);
            record.setAlignment(Pos.CENTER);

            Label noOfProcesseslbl = new Label("P"+ (i+1));

            arrivaltimetf[i] = new TextField();
            arrivaltimetf[i].setMaxWidth(110);
            cpubursttf[i] = new TextField();
            cpubursttf[i].setMaxWidth(110);
            prioritytf[i] = new TextField();
            prioritytf[i].setMaxWidth(110);
            if(i==0){
                record.getChildren().addAll(new VBox(20,no,noOfProcesseslbl), new VBox(20,at,arrivaltimetf[i]), new VBox(20,ct,cpubursttf[i]), new VBox(20,pt,prioritytf[i]));
            }else {

                record.getChildren().addAll(noOfProcesseslbl, arrivaltimetf[i], cpubursttf[i], prioritytf[i]);
            }
            getChildren().add(record);
        }

        getChildren().add(errorLBL);
        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(event -> {
            if(areValid(arrivaltimetf,cpubursttf,prioritytf)){
                ArrayList<Process> processes = new ArrayList<>();
                for (int i = 0; i < processNo; i++) {
                    Process process = new Process();
                    process.setNumber(i + 1);
                    process.setArrivalTime(Integer.parseInt(arrivaltimetf[i].getText()));
                    process.setBurstTime(Integer.parseInt(cpubursttf[i].getText()));
                    process.setPriority(Integer.parseInt(prioritytf[i].getText()));

                    processes.add(process);
                }

                Main.navigateTo(new ResultPage(processes));
            }else{
                errorLBL.setVisible(true);

            }
        });
        getChildren().add(submitBtn);

    }

    private Boolean isValid(String text){
        try {
            int num = Integer.parseInt(text);
            if(num < 0)
                return false;

        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private Boolean areValid(TextField arrivaltimetf[], TextField cpubursttf[], TextField prioritytf[]){

        for(int i = 0; i < Integer.parseInt(processNoTF.getText()); i++) {
            if(!isValid(arrivaltimetf[i].getText()) || !isValid(cpubursttf[i].getText()) || !isValid(prioritytf[i].getText())){
                return false;
            }
        }
        return true;
    }

}
