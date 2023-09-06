package org.example.UI;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainWindow extends Application {
    private final Button[] numberButton = new Button[4];
    private final Button addButton = new Button("+");
    private final Button subtractButton = new Button("-");
    private final Button multiplyButton = new Button("*");
    private final Button divideButton = new Button("/");
    private final Button computeButton = new Button("=");

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void init() throws Exception {
        super.init();
        for(int i = 0; i < 4; i++) {
            numberButton[i] = new Button(String.valueOf(i));
            numberButton[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }


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
        totalView.getChildren().addAll(
                label,
                createDataEntryControl()
        );
        return totalView;
    }

    private Label setupDisplay(VBox totalView) {
        Label display = new Label("8");
        display.setPrefWidth(totalView.getPrefWidth());
        display.setPrefHeight(totalView.getPrefHeight()/4);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        return display;
    }

    private Parent createDataEntryControl() {
        GridPane gridPane = new GridPane();
        Button[][] layout = {
                new Button[] {
                    numberButton[3], numberButton[2], multiplyButton
                },
                new Button[] {
                    numberButton[1], numberButton[0], divideButton
                },
                new Button[] {
                    addButton, subtractButton, computeButton
                }
        };
        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 3; i++) {
                gridPane.add(layout[i][j], j, i);
            }
        }
        return gridPane;
    }
}