module org.example.angentsmaintenancejdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.feesmaintenancejdbc to javafx.fxml;
    exports org.example.feesmaintenancejdbc;
}