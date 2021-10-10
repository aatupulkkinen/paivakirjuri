module otp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jasypt;


    exports otp;
    opens otp.controllers to javafx.fxml;
    opens otp.model.entities to org.hibernate.orm.core;
    opens otp.model.daos;
}