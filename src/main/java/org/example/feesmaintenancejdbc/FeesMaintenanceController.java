package org.example.feesmaintenancejdbc;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FeesMaintenanceController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdd"
    private Button btnAdd; // Value injected by FXMLLoader

    @FXML // fx:id="colFeeAmount"
    private TableColumn<Fee, Double> colFeeAmount; // Value injected by FXMLLoader

    @FXML // fx:id="colFeeDescription"
    private TableColumn<Fee, String> colFeeDescription; // Value injected by FXMLLoader

    @FXML // fx:id="colFeeId"
    private TableColumn<Fee, String> colFeeId; // Value injected by FXMLLoader

    @FXML // fx:id="colFeeName"
    private TableColumn<Fee, String> colFeeName; // Value injected by FXMLLoader

    @FXML // fx:id="tvFee"
    private TableView<Fee> tvFee; // Value injected by FXMLLoader
    private ObservableList<Fee> data = FXCollections.observableArrayList();

    private String mode; // Add or Edit

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'fees-maintenance-view.fxml'.";
        assert colFeeAmount != null : "fx:id=\"colFeeAmount\" was not injected: check your FXML file 'fees-maintenance-view.fxml'.";
        assert colFeeDescription != null : "fx:id=\"colFeeDescription\" was not injected: check your FXML file 'fees-maintenance-view.fxml'.";
        assert colFeeId != null : "fx:id=\"colFeeId\" was not injected: check your FXML file 'fees-maintenance-view.fxml'.";
        assert colFeeName != null : "fx:id=\"colFeeName\" was not injected: check your FXML file 'fees-maintenance-view.fxml'.";
        assert tvFee != null : "fx:id=\"tvFee\" was not injected: check your FXML file 'fees-maintenance-view.fxml'.";

        // set up table columns
        colFeeId.setCellValueFactory(new PropertyValueFactory<Fee, String>("feeId"));
        colFeeName.setCellValueFactory(new PropertyValueFactory<Fee, String>("feeName"));
        colFeeAmount.setCellValueFactory(new PropertyValueFactory<Fee, Double>("feeAmt"));
        colFeeDescription.setCellValueFactory(new PropertyValueFactory<Fee, String>("feeDesc"));
        displayFees();
        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mode = "Add";
                openDialog(null, mode); // no feex at beginning of add
            }
        });

        tvFee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Fee>() {
            @Override
            public void changed(ObservableValue<? extends Fee> observableValue, Fee fee, Fee t1) {
                int index = tvFee.getSelectionModel().getSelectedIndex();
                // check when it loses or gains selection
                if(tvFee.getSelectionModel().isSelected(index)){
//                    mode = "Edit";
//                    openDialog(t1, mode);
                    // open the dialog in a separate thread - to avoid error when dialog closes
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            mode = "Edit";
                            openDialog(t1, mode);
                        }
                    }); //whenever possible to run the task, run it
                }
            }
        });
    } // end initialize

    // method to display fees in the table view
    private void displayFees(){
        data.clear();
        try {
            data = FeeDB.getFees();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tvFee.setItems(data);
    }

    // method to open dialog with a selected fee if there is one, and using add/edit mode
    private void openDialog(Fee fee, String mode) {
        FXMLLoader fxmlLoader = new FXMLLoader(FeesMaintenanceApplication.class.getResource("dialog-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DialogController dialogController = fxmlLoader.getController();
        dialogController.setMode(mode);
        if(mode.equals("Edit")) { // fill the text fields in dialog
            dialogController.displayFee(fee);
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait(); // waits until user is done with second scene
        displayFees();
    }


} // end class
