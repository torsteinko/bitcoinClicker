package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class Controller{

    Main clicker;
    Shop shop;

    @FXML
    Text counterOutput;

    @FXML
    void initialize() {
        clicker = new Main();
        shop = new Shop();
        updateGUI();
    }

    @FXML
    void countAction() {
        clicker.bitcoinOnClick();
        counterOutput.setText(String.format("%.0f BTCs", clicker.getTotalBitcoins()));
    }

    @FXML
    void buyAbakusAction() {
        shop.buyAbakus();
    }
    @FXML
    void buyPascalineAction() {
        shop.buyPascaline();
    }
    @FXML
    void buyENIACAction() {
        shop.buyEniac();
    }
    @FXML
    void buyTRADICAction() {
        shop.buyTradic();
    }
    @FXML
    void buyAppleIIAction() {
        shop.buyAppleII();
    }
    @FXML
    void buyCommodore64Action() {
        shop.buyCommodore64();
    }
    @FXML
    void buyAppleMacintoshAction() {
        shop.buyAppleMacintosh();
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
                        counterOutput.setText(String.format("%.0f BTCs", clicker.getTotalBitcoins()));
                    }
                });
            }
        };

        timer.schedule(updateRate, 0, 20);
    }
}

