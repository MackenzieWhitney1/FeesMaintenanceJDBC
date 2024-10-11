package org.example.feesmaintenancejdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AgentsMaintenanceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                AgentsMaintenanceApplication.class.getResource("agentsmaintenance-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Agents Maintenance");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}