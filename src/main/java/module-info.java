module com.oop.voyage.project_voyage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.oop.voyage.project_voyage to javafx.fxml;
    opens com.oop.voyage.project_voyage.model to javafx.fxml;
    opens com.oop.voyage.project_voyage.services to javafx.fxml;
    opens com.oop.voyage.project_voyage.interfaces to javafx.fxml;

    exports com.oop.voyage.project_voyage;
    exports com.oop.voyage.project_voyage.model;
    exports com.oop.voyage.project_voyage.services;
    exports com.oop.voyage.project_voyage.interfaces;
}