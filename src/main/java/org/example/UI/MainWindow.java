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
    private final String[] OPERATIONS = new String[]{"+", "-", "*", "/", "^", "\u221A"};
    private final Button[] numberButtons = new Button[4];
    private final Button addButton = new Button(OPERATIONS[0]);
    private final Button subtractButton = new Button(OPERATIONS[1]);
    private final Button multiplyButton = new Button(OPERATIONS[2]);
    private final Button divideButton = new Button(OPERATIONS[3]);
    private final Button powerButton = new Button(OPERATIONS[4]);
    private final Button squareRootButton = new Button(OPERATIONS[5]);
    private final Button clearButton = new Button("C");
    private final Button convertButton = new Button("Convert");
    private final Button computeButton = new Button("=");
    private final Label display = new Label("");
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Controller controller = new Controller();

    private boolean isNumberConverted = false;

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
        powerButton.setOnAction(clicked -> fireOperationPressed(powerButton.getText()));
        computeButton.setOnAction(clicked -> computePressed());
        clearButton.setOnAction(clicked -> clearPressed());
        convertButton.setOnAction(clicked -> convertPressed());
        squareRootButton.setOnAction(clicked -> computeSqrtPressed());
    }

    private void setupOperatorButtons(int numOfButtonsPerRow) {
        addButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        subtractButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        multiplyButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        divideButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        computeButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        powerButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
        squareRootButton.setPrefSize((double)WINDOW_WIDTH / numOfButtonsPerRow, (double)LABEL_HEIGHT / numOfButtonsPerRow);
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
        if(isNumberConverted) {
            display.setText(String.valueOf(number));
            isNumberConverted = false;
        } else {
            String output = display.getText() + number;
            display.setText(output);
        }
    }

    public void computeSqrtPressed() {
        String output = display.getText() + "\u221A";
        display.setText(output);
        computePressed();
    }

    public void fireOperationPressed(String operation) {
        if (operatorInUse() || isNumberConverted || display.getText().isEmpty()) {
            return;
        }
        String output = display.getText() + operation;
        display.setText(output);
    }

    private boolean operatorInUse(){
        for(String operation: OPERATIONS) {
            if(display.getText().contains(operation)) {
                return true;
            }
        }
        return false;
    }

    private void computePressed() {
        try {
            String result = controller.compute(display.getText());
            display.setText(result);
        } catch (ArithmeticException e) {
            ErrorWindow divideByZero = new ErrorWindow ("Cannot divide by zero");
            divideByZero.display();
            display.setText("");
        }
    }

    private void clearPressed() {
        display.setText("");
        isNumberConverted = false;
    }

    private void convertPressed() {
        if(operatorInUse()) {
            return;
        }
        if(isNumberConverted){
            display.setText(controller.convertBack(display.getText()));
            isNumberConverted = false;
            return;
        }
        display.setText(controller.convert(display.getText()));
        isNumberConverted = true;
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
                    numberButtons[3], numberButtons[2], multiplyButton, powerButton
                },
                new Button[] {
                    numberButtons[1], numberButtons[0], divideButton, squareRootButton
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
