package com.example.tidsrejseagenturet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Sørg for, at stien er korrekt – f.eks. hvis FXML-filen ligger i resources-mappen
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/tidsrejseagenturet/view/hello-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Tidsrejseagenturet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
