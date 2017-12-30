package com.frsummit.airportreservation;

import com.frsummit.airportreservation.util.HibernateUtil;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainApp extends Application {
    
//    public static double origW;
//    public static double origH;

    private static Stage mainStage;

    public static Stage getStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) throws Exception {

        mainStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Region contentRootRegion = (Region) loader.load();
//        origW = 600;
//        origH = 400;
        StackPane rootPane = new StackPane();
        Scene scene = new Scene(contentRootRegion);
        stage.setTitle("Airport Reservation");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest((event) -> {
            HibernateUtil.getSessionFactory().close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
