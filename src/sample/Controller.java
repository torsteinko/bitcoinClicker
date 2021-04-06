package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Controller {

    Main main;

    //FXML
    @FXML
    Text counterOutput;
    @FXML
    Text perSecDisplay;
    @FXML
    Text abakusCountField;
    @FXML
    Text pascalineCountField;
    @FXML
    Text eniacCountField;
    @FXML
    Text tradicCountField;
    @FXML
    Text appleIICountField;
    @FXML
    Text commodore64CountField;
    @FXML
    Text appleMacintoshCountField;
    @FXML
    Text cursorLevelCount;
    @FXML
    Button cursorUpgradeButton;
    @FXML
    Button abakusButtonBuy;
    @FXML
    Button pascalineButtonBuy;
    @FXML
    Button eniacButtonBuy;
    @FXML
    Button tradicButtonBuy;
    @FXML
    Button appleIIButtonBuy;
    @FXML
    Button commodore64ButtonBuy;
    @FXML
    Button appleMacintoshButtonBuy;

    //Initialiserer viktige
    @FXML
    void initialize() {
        main = new Main();
        main.shop.updateShopAtBuy();
        updateGUI();
        updateFields();
    }

    //Funksjon som knyttes til klikk på bitcoin i GUI
    @FXML
    void countAction() {
        main.shop.bitcoinOnClick();
        counterOutput.setText(String.format("%.1f BTCs", main.shop.getTotalBitcoins()));
    }

    //Kjøp funksjoner som knyttes til knapper i GUI
    @FXML
    void buyCursorAction() {
        main.shop.buyCursor();
        updateFields();
    }
    @FXML
    void buyAbakusAction() {
        main.shop.buyAbakus();
        updateFields();
    }
    @FXML
    void buyPascalineAction() {
        main.shop.buyPascaline();
        updateFields();
    }
    @FXML
    void buyENIACAction() {
        main.shop.buyEniac();
        updateFields();
    }
    @FXML
    void buyTRADICAction() {
        main.shop.buyTradic();
        updateFields();
    }
    @FXML
    void buyAppleIIAction() {
        main.shop.buyAppleII();
        updateFields();
    }
    @FXML
    void buyCommodore64Action() {
        main.shop.buyCommodore64();
        updateFields();
    }
    @FXML
    void buyAppleMacintoshAction() {
        main.shop.buyAppleMacintosh();

    }

    //Oppdaterer tekstfelt som ikkje må oppdateres kontinuerlig (BTCs/s og knapper)
    void updateFields() {
        //Oppdaterer per sec
        perSecDisplay.setText(String.format("%.1f BTCs/s", main.shop.getBitcoinsPerSec()));
        //Oppdaterer pris
        cursorUpgradeButton.setText(String.format("%.0f",main.shop.getCursorPrice()));
        abakusButtonBuy.setText(String.format("%.0f", main.shop.getAbakusPrice()));
        pascalineButtonBuy.setText(String.format("%.0f", main.shop.getPascalinePrice()));
        eniacButtonBuy.setText(String.format("%.0f", main.shop.getEniacPrice()));
        tradicButtonBuy.setText(String.format("%.0f",main.shop.getTradicPrice()));
        appleIIButtonBuy.setText(String.format("%.0f", main.shop.getAppleIIPrice()));
        commodore64ButtonBuy.setText(String.format("%.0f", main.shop.getCommodore64Price()));
        appleMacintoshButtonBuy.setText(String.format("%.0f", main.shop.getAppleMacintoshPrice()));
        //Oppdaterer count
        cursorLevelCount.setText(String.format("Level: %d",main.shop.getCursorLevelCount()));
        abakusCountField.setText(String.format("%d",main.shop.getAbakusCount()));
        pascalineCountField.setText(String.format("%d",main.shop.getPascalineCount()));
        eniacCountField.setText(String.format("%d",main.shop.getEniacCount()));
        tradicCountField.setText(String.format("%d",main.shop.getTradicCount()));
        appleIICountField.setText(String.format("%d",main.shop.getAppleIICount()));
        commodore64CountField.setText(String.format("%d",main.shop.getCommodore64Count()));
        appleMacintoshCountField.setText(String.format("%d",main.shop.getAppleMacintoshCount()));

    }

    //Oppdaterer felt som må oppdateres kontinuerlig (Total bitcoins)
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
                        counterOutput.setText(String.format("%.1f BTCs", main.shop.getTotalBitcoins()));
                    }
                });
            }
        };

        timer.schedule(updateRate, 0, 20);
    }
}

