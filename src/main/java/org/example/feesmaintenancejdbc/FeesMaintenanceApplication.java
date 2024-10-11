package org.example.feesmaintenancejdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application to manage fees in the Travel Experts database
 * Author: Mackenzie Whitney
 * Date: September 2024
 */
public class FeesMaintenanceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FeesMaintenanceApplication.class.getResource("fees-maintenance-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fees Maintenance");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}