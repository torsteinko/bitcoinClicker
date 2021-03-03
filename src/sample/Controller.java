package sample;

import javafx.application.Platform;
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
        updateGUI();
    }

    @FXML
    void countAction() {
        clicker.bitcoinOnClick();
        counterOutput.setText(String.format("Antall bitcoins: %.0f", clicker.getTotalBitcoins()));
    }

    @FXML
    void updateGUI() {
        Timer timer = new Timer();
        TimerTask updateRate = new TimerTask() {
            @Override
            public void run() {
                //runLater to be able to use Timers in Controller
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        counterOutput.setText(String.format("Antall bitcoins: %.0f", clicker.getTotalBitcoins()));
                    }
                });
            }
        };

        timer.schedule(updateRate, 0, 20);
    }

}

