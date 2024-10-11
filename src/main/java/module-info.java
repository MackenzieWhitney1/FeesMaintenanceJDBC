module org.example.feesmaintenancejdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.feesmaintenancejdbc to javafx.fxml;
    exports org.example.feesmaintenancejdbc;
}