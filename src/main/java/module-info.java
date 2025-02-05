module com.example.tidsrejseagenturet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tidsrejseagenturet to javafx.fxml;
    exports com.example.tidsrejseagenturet;
    exports com.example.tidsrejseagenturet.View;
    opens com.example.tidsrejseagenturet.View to javafx.fxml;
}