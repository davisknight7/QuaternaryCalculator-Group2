package org.example.UI;

import org.example.controller.Controller;

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
    private final int WINDOW_WIDTH = 400;
    private final int LABEL_HEIGHT = 600;
    private final String[] operations = new String[]{"+", "-", "*", "/", "^", "sqrt"};
    private final Button[] numberButtons = new Button[4];
    private final Button addButton = new Button(operations[0]);
    private final Button subtractButton = new Button(operations[1]);
    private final Button multiplyButton = new Button(operations[2]);
    private final Button divideButton = new Button(operations[3]);
    private final Button squareButton = new Button(operations[4]);
    private final Button rootButton = new Button(operations[5]);
    private final Button clearButton = new Button("C");
    private final Button convertButton = new Button("Convert");
    private final Button computeButton = new Button("=");
    private final Label display = new Label("");
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Controller controller = new Controller();

    @Override
    public void init() throws Exception {
        super.init();
        int numOfButtonsPerRow = 4;
        setupOperatorButtons(numOfButtonsPerRow);
        setupNumberButtons(numOfButtonsPerRow);
        addButton.setOnAction(clicked -> fireOperationPressed(addButton.getText()));
        subtractButton.setOnAction(clicked -> fireOperationPressed(subtractButton.getText()));
        multiplyButton.setOnAction(clicked -> fireOperationPressed(multiplyButton.getText()));
        divideButton.setOnAction(clicked -> fireOperationPressed(divideButton.getText()));
        computeButton.setOnAction(clicked -> computePressed());
    }

    private void setupOperatorButtons(int numOfButtonsPerRow) {
        addButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        subtractButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        multiplyButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        divideButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        computeButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        squareButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        rootButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        clearButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        convertButton.setPrefSize(WINDOW_WIDTH, (double)LABEL_HEIGHT / numOfButtonsPerRow);
    }

    private void setupNumberButtons(int numOfButtonsPerRow) {
        for(int i = 0; i < 4; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
            int finalI = i;
            numberButtons[i].setOnAction(clicked -> fireNumButtonPressed(finalI));
        }
    }

    public void fireNumButtonPressed(int number) {
        String output = display.getText() + number;
        display.setText(output);
    }

    public void fireOperationPressed(String operation) {
        if (operatorInUse()) {
            return;
        }
        String output = display.getText() + operation;
        display.setText(output);
    }

    private boolean operatorInUse(){
        for(String operation: operations) {
            if(display.getText().contains(operation)) {
                return true;
            }
        }
        return false;
    }

    private void computePressed() {
        String result = controller.compute(display.getText());
        display.setText(result);
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
        totalView.setPrefWidth(WINDOW_WIDTH);
        totalView.setPrefHeight(LABEL_HEIGHT);
        Label label = setupDisplay(totalView);
        totalView.getChildren().addAll(
                label,
                createDataEntryControl()
        );
        return totalView;
    }

    private Label setupDisplay(VBox totalView) {
        display.setPrefWidth(totalView.getPrefWidth());
        display.setPrefHeight(totalView.getPrefHeight()/4);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 40));
        return display;
    }

    private Parent createDataEntryControl() {
        GridPane gridPane = new GridPane();
        Button[][] layout = {
                new Button[] {
                    numberButtons[3], numberButtons[2], multiplyButton, squareButton
                },
                new Button[] {
                    numberButtons[1], numberButtons[0], divideButton, rootButton
                },
                new Button[] {
                    addButton, subtractButton, computeButton, clearButton
                }
        };
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 3; i++) {
                gridPane.add(layout[i][j], j, i);
            }
        }
        gridPane.add(convertButton, 0, 4, 4, 1);
        return gridPane;
    }
}
