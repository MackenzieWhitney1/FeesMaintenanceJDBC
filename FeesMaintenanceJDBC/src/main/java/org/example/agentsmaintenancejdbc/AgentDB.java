package org.example.feesmaintenancejdbc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AgentDB {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/travelexperts";
        String user = "jolanta";
        String password = "password";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e){
            throw new RuntimeException("Problem with database connection: " +e.getMessage());
        }
        return conn;
    } //end getConnection

    public static ObservableList<Agent> getAgents() throws SQLException {
        ObservableList<Agent> agents = FXCollections.observableArrayList();
        Agent agent; // for processing data
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from agents");
        while(rs.next()){
            agent = new Agent(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8)
            );
            agents.add(agent);
        }
        conn.close();
        return agents;
    } // end getAgents


} // end class
