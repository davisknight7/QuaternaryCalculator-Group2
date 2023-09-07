package org.example.UI;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ErrorWindow {
    private final Alert Error;

    public ErrorWindow (String errorReason) {
        Error = new Alert (Alert.AlertType.ERROR, errorReason);
    }


    public void display() {
        Error.showAndWait();
    }
}
