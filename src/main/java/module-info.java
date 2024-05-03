module com.example.in_n_out_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;


    exports app;
    opens app to javafx.fxml;

    exports controller;
    opens controller;
    exports controller.importBill;
    opens controller.importBill;
    exports controller.product;
    opens controller.product;
}