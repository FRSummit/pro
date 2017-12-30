package com.frsummit.hospitalmgt;

import com.frsummit.hospitalmgt.util.HibernateUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainApp extends Application {
    
    private static Stage mainStage;

    public static Stage getStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) throws Exception {

        mainStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));

        Region contentRootRegion = (Region) loader.load();
        StackPane rootPane = new StackPane();
        Scene scene = new Scene(contentRootRegion);
        stage.setTitle("BLOOD DONATE MANAGEMENT");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest((WindowEvent we) -> {
            HibernateUtil.getSessionFactory().close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
