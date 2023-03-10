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
    requires com.google.gson;

    exports otp;
    opens otp.controllers;
    opens otp.model.entities to org.hibernate.orm.core;
    opens otp.model.daos;
    opens otp.model.daos.mark;
    opens otp.model.daos.quote;
    opens otp.model.daos.image;
}