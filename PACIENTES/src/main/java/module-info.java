module org.example.pacientes {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports org.example.pacientes;
    exports org.example.pacientes.modelo;
    exports org.example.pacientes.controlador;

    opens org.example.pacientes to javafx.fxml;
    opens org.example.pacientes.modelo to javafx.base;
    opens org.example.pacientes.controlador to javafx.fxml;
}