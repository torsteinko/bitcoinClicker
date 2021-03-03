package sample;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    Main clicker;

    @FXML
    Text counterOutput;

    @FXML
    void initialize() {
        clicker = new Main();
    }

    @FXML
    void countAction() {
        clicker.bitcoinOnClick();
        counterOutput.setText("Antall bitcoins: " + clicker.getTotalBitcoins());
    }

//    public void timerScreenUpdate() {
//        Timer timerGUI = new Timer();
//        TimerTask updateRate = new TimerTask() {
//            @Override
//            public void run() {
//                counterOutput.setText("Antall bitcoins: " + clicker.getTotalBitcoins());
//            }
//        };
//
//        timerGUI.schedule(updateRate, 0, 20);
//    }

}

