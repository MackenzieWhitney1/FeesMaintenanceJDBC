
package org.example.feesmaintenancejdbc;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AgentsMaintenanceController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdd"
    private Button btnAdd; // Value injected by FXMLLoader

    @FXML // fx:id="colAgencyId"
    private TableColumn<Agent, Integer> colAgencyId; // Value injected by FXMLLoader

    @FXML // fx:id="colAgentId"
    private TableColumn<Agent, Integer> colAgentId; // Value injected by FXMLLoader

    @FXML // fx:id="colAgtBUsPhone"
    private TableColumn<Agent, String> colAgtBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="colAgtEmail"
    private TableColumn<Agent, String> colAgtEmail; // Value injected by FXMLLoader

    @FXML // fx:id="colAgtFirstName"
    private TableColumn<Agent, String> colAgtFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="colAgtLastName"
    private TableColumn<Agent, String> colAgtLastName; // Value injected by FXMLLoader

    @FXML // fx:id="colAgtMiddleInitial"
    private TableColumn<Agent, String> colAgtMiddleInitial; // Value injected by FXMLLoader

    @FXML // fx:id="colAgtPosition"
    private TableColumn<Agent, String> colAgtPosition; // Value injected by FXMLLoader

    @FXML // fx:id="tvAgents"
    private TableView<Agent> tvAgents; // Value injected by FXMLLoader

    private ObservableList<Agent> data = FXCollections.observableArrayList();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgencyId != null : "fx:id=\"colAgencyId\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgentId != null : "fx:id=\"colAgentId\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgtBusPhone != null : "fx:id=\"colAgtBUsPhone\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgtEmail != null : "fx:id=\"colAgtEmail\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgtFirstName != null : "fx:id=\"colAgtFirstName\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgtLastName != null : "fx:id=\"colAgtLastName\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgtMiddleInitial != null : "fx:id=\"colAgtMiddleInitial\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert colAgtPosition != null : "fx:id=\"colAgtPosition\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";
        assert tvAgents != null : "fx:id=\"tvAgents\" was not injected: check your FXML file 'agentsmaintenance-view.fxml'.";

        // set up table columns
        colAgentId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agentId"));
        colAgtFirstName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtFirstName"));
        colAgtMiddleInitial.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtMiddleInitial"));
        colAgtLastName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtLastName"));
        colAgtBusPhone.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtBusPhone"));
        colAgtEmail.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtEmail"));
        colAgtPosition.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtPosition"));
        colAgencyId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agencyId"));
        displayAgents();
    } // end initialize

    private void displayAgents(){
        data.clear();
        try {
            data = AgentDB.getAgents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tvAgents.setItems(data);
    }

} // end class
