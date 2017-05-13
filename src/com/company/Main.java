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

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Priority non-preemptive");
        RegisterPage registerPage = new RegisterPage();
        ScrollPane scrollPane = new ScrollPane(registerPage);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        stage.setScene(new Scene(scrollPane,960,600));
        stage.show();
    }
    public static void navigateTo (Parent node){
        stage.setScene(new Scene(node,960,600));
    }
}
