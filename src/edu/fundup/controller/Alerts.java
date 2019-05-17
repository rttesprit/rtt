package edu.fundup.controller;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public interface Alerts {
    void showAlert(Alert.AlertType alertType, Window owner, String title, String message);
}
