package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;


public class Controller {

    Shop shop;

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
        shop = new Shop();
        shop.updateShopAtBuy();
        updateGUI();
        updateFields();
    }

    //Funksjon som knyttes til klikk på bitcoin i GUI
    @FXML
    void countAction() {
        shop.bitcoinOnClick();
        counterOutput.setText(String.format("%.0f BTCs", shop.getTotalBitcoins()));
    }

    //Kjøp funksjoner som knyttes til knapper i GUI
    @FXML
    void buyAbakusAction() {
        shop.buyAbakus();
        updateFields();
    }
    @FXML
    void buyPascalineAction() {
        shop.buyPascaline();
        updateFields();
    }
    @FXML
    void buyENIACAction() {
        shop.buyEniac();
        updateFields();
    }
    @FXML
    void buyTRADICAction() {
        shop.buyTradic();
        updateFields();
    }
    @FXML
    void buyAppleIIAction() {
        shop.buyAppleII();
        updateFields();
    }
    @FXML
    void buyCommodore64Action() {
        shop.buyCommodore64();
        updateFields();
    }
    @FXML
    void buyAppleMacintoshAction() {
        shop.buyAppleMacintosh();

    }

    //Oppdaterer tekstfelt som ikkje må oppdateres kontinuerlig (BTCs/s og knapper)
    void updateFields() {
        //Oppdaterer per sec
        perSecDisplay.setText(String.format("%.1f BTCs/s", shop.getBitcoinsPerSec()));
        //Oppdaterer pris
        abakusButtonBuy.setText(String.format("%.0f", shop.getAbakusPrice()));
        pascalineButtonBuy.setText(String.format("%.0f", shop.getPascalinePrice()));
        eniacButtonBuy.setText(String.format("%.0f", shop.getEniacPrice()));
        tradicButtonBuy.setText(String.format("%.0f", shop.getTradicPrice()));
        appleIIButtonBuy.setText(String.format("%.0f", shop.getAppleIIPrice()));
        commodore64ButtonBuy.setText(String.format("%.0f", shop.getCommodore64Price()));
        appleMacintoshButtonBuy.setText(String.format("%.0f", shop.getAppleMacintoshPrice()));
        //Oppdaterer count
        abakusCountField.setText(String.format("%d",shop.getAbakusCount()));
        pascalineCountField.setText(String.format("%d",shop.getPascalineCount()));
        eniacCountField.setText(String.format("%d",shop.getEniacCount()));
        tradicCountField.setText(String.format("%d",shop.getTradicCount()));
        appleIICountField.setText(String.format("%d",shop.getAppleIICount()));
        commodore64CountField.setText(String.format("%d",shop.getCommodore64Count()));
        appleMacintoshCountField.setText(String.format("%d",shop.getAppleMacintoshCount()));
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
                        counterOutput.setText(String.format("%.0f BTCs", shop.getTotalBitcoins()));
                    }
                });
            }
        };

        timer.schedule(updateRate, 0, 20);
    }
}

