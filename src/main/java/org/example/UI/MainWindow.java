package org.example.UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainWindow extends Application {
    private final Label display = new Label("8");
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
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
            display
        );
        return vBox;
    }
}
