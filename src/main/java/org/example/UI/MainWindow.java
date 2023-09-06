package org.example.UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainWindow extends Application {
    private final Button addButton = new Button("+");
    private final Button subtractButton = new Button("-");
    private final Button multiplyButton = new Button("*");
    private final Button divideButton = new Button("/");

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void start(Stage primaryStage) {
        setUp(primaryStage);
    }

    private void setUp(Stage primaryStage) {
        primaryStage.setTitle("Quaternary Calculator");
        primaryStage.setScene(new Scene(createCalculatorView()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(X -> {
            executor.shutdown();
            primaryStage.close();
            Platform.exit();
        });
    }

    private Parent createCalculatorView() {
        VBox totalView = new VBox();
        totalView.setPrefWidth(400);
        totalView.setPrefHeight(600);

        Label label = setupDisplay(totalView);
        HBox rowOneOperations = setupRowOneOperations(totalView);

        totalView.getChildren().addAll(label, rowOneOperations);

        return totalView;
    }

    private HBox setupRowOneOperations(VBox totalView) {
        HBox row = new HBox();

        addButton.setPrefSize(totalView.getPrefWidth()/4, totalView.getPrefHeight()/4);
        subtractButton.setPrefSize(totalView.getPrefWidth()/4, totalView.getPrefHeight()/4);
        multiplyButton.setPrefSize(totalView.getPrefWidth()/4, totalView.getPrefHeight()/4);
        divideButton.setPrefSize(totalView.getPrefWidth()/4, totalView.getPrefHeight()/4);
        row.getChildren().addAll(addButton, subtractButton, multiplyButton, divideButton);

        return row;
    }

    private Label setupDisplay(VBox totalView) {
        Label display = new Label("8");
        display.setPrefWidth(totalView.getPrefWidth());
        display.setPrefHeight(totalView.getPrefHeight()/4);

        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        return display;
    }
}
