module com.example.projectedusphere {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.projectedusphere to javafx.fxml;
    exports com.example.projectedusphere;
}