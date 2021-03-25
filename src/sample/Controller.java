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


public class Controller {

    Shop shop;

    @FXML
    Text counterOutput;
    @FXML
    Text perSecDisplay;

    @FXML
    void initialize() {
        shop = new Shop();
        updateGUI();
        shop.updateShopAtBuy();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
    }

    @FXML
    void countAction() {
        shop.bitcoinOnClick();
        counterOutput.setText(String.format("%.0f BTCs", shop.getTotalBitcoins()));
    }

    @FXML
    void buyAbakusAction() {
        shop.buyAbakus();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
    }
    @FXML
    void buyPascalineAction() {
        shop.buyPascaline();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
    }
    @FXML
    void buyENIACAction() {
        shop.buyEniac();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
    }
    @FXML
    void buyTRADICAction() {
        shop.buyTradic();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
    }
    @FXML
    void buyAppleIIAction() {
        shop.buyAppleII();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
    }
    @FXML
    void buyCommodore64Action() {
        shop.buyCommodore64();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
    }
    @FXML
    void buyAppleMacintoshAction() {
        shop.buyAppleMacintosh();
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
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
                        counterOutput.setText(String.format("%.0f BTCs", shop.getTotalBitcoins()));
                    }
                });
            }
        };

        timer.schedule(updateRate, 0, 20);
    }
}

