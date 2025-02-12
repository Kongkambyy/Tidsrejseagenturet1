package com.example.tidsrejseagenturet;

import com.example.tidsrejseagenturet.Model.GuideDatabaseHandler;
import com.example.tidsrejseagenturet.Model.TimeMachineDatabaseHandler;
import com.example.tidsrejseagenturet.Model.TimePeriods;
import com.example.tidsrejseagenturet.Model.TimePeriodsDatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tidsrejseagenturet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}