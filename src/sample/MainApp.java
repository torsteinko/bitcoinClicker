package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            if (SaveHandler.readFromFile() != null) {
                Controller.setObject((Main) SaveHandler.readFromFile());
            }
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Bitcoin Clicker");
            primaryStage.setScene(new Scene(root, 640, 480));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception{
        //Legg til lagring
        SaveHandler.writeToFile(Controller.getObject());
        //Går utav programmet
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
