module com.example.comp2522202430termprojectyeali {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.comp2522202430termprojectyeali to javafx.fxml;
    exports com.example.comp2522202430termprojectyeali;
}