module em_ims.em_inventorymanagementsoftware {
    requires javafx.controls;
    requires javafx.fxml;
//    requires javafx.web;
    requires javafx.graphics;
    requires java.sql;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;

    opens em_ims.em_inventorymanagementsoftware to javafx.fxml;
    exports em_ims.em_inventorymanagementsoftware;
    exports Model;
    opens Model to javafx.fxml;
}