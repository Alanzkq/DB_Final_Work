module com.example.work {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mssql.jdbc;



    opens com.example.work to javafx.fxml;
    opens Pojo to javafx.base;
    exports com.example.work;
}