module org.example.pafpoo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.pafpoo to javafx.fxml;
    exports org.example.pafpoo;
}