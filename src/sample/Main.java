package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.*;

import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Bitcoin Clicker");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
    //Variabler
    private double totalBitcoins;
    private double bitcoinsPerSec = 1;
    private int bitcoinsPerClick = 1;

    //Konstruktør
    public Main() {
        //Starter timer
        timerUpdate();
    }

    //Getters og setters
    public double getTotalBitcoins() {
        return totalBitcoins;
    }

    public double getBitcoinsPerSec() {
        return bitcoinsPerSec;
    }

    public double getBitcoinsPerClick() {
        return bitcoinsPerClick;
    }

    public void setBitcoinsPerSec(double bitcoinsPerSec) {
        this.bitcoinsPerSec = bitcoinsPerSec;
    }

    public void setBitcoinsPerClick(int bitcoinsPerClick) {
        this.bitcoinsPerClick = bitcoinsPerClick;
    }

    public void setTotalBitcoins(double totalBitcoins) {
        this.totalBitcoins = totalBitcoins;
    }

    //Oppdaterer totalBitcoins med bitcoinsPerClick
    public void bitcoinOnClick() {
        setTotalBitcoins(getTotalBitcoins() + getBitcoinsPerClick());
    }

    //Timer oppdaterer totalBitcoins med bitcoinsPerSec
    public void timerUpdate() {
        Timer timer = new Timer();
        TimerTask updateRate = new TimerTask() {
            @Override
            public void run() {
                setTotalBitcoins(getTotalBitcoins()+(getBitcoinsPerSec()/50));
            }
        };
        timer.schedule(updateRate, 0, 20);
    }

    public static void main(String[] args) {
        launch(args);
        Main test = new Main();
    }
}
