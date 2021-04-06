package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.*;

public class Main extends Application implements Serializable {

    Shop shop = new Shop();

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            shop = (Shop) SaveHandler.readFromFile("src/sample/data.txt");
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
        SaveHandler.writeToFile(shop);
        //Går utav programmet
        System.exit(0);
    }

    //Konstruktør
    public Main() {
        //Start timer
        shop.timerUpdate();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
