package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    //Initialiserer som static for å kunne aksesere i MainApp
    //Dette sånn at lagring og gjenoppretting kan trigges fra start() og stop()
    private static Main main = new Main();

    //FXML referanser til sample
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
    @FXML
    Button temp1;
    @FXML
    Button temp2;
    @FXML
    Button temp3;
    @FXML
    Button temp4;
    @FXML
    Button temp5;
    @FXML
    Button startNewGameButton;

    //Initialiserer viktige
    @FXML
    void initialize() {
        main.updateShopAtBuy();
        main.timerUpdate();
        updateGUI();
        updateFields();
        disableTempButtons();
    }

    //Funksjon som knyttes til klikk på bitcoin i GUI
    @FXML
    void countAction() {
        main.bitcoinOnClick();
        counterOutput.setText(String.format("%.1f BTCs", main.getTotalBitcoins()));
        System.out.println("press");
    }

    @FXML
    void keyRelease() {
        System.out.println("release");

    }

    //Kjøp funksjoner som knyttes til knapper i GUI
    @FXML
    void buyCursorAction() {
        main.buyCursor();
        updateFields();
    }
    @FXML
    void buyAbakusAction() {
        main.buyAbakus();
        updateFields();
    }
    @FXML
    void buyPascalineAction() {
        main.buyPascaline();
        updateFields();
    }
    @FXML
    void buyENIACAction() {
        main.buyEniac();
        updateFields();
    }
    @FXML
    void buyTRADICAction() {
        main.buyTradic();
        updateFields();
    }
    @FXML
    void buyAppleIIAction() {
        main.buyAppleII();
        updateFields();
    }
    @FXML
    void buyCommodore64Action() {
        main.buyCommodore64();
        updateFields();
    }
    @FXML
    void buyAppleMacintoshAction() {
        main.buyAppleMacintosh();

    }

    //Oppdaterer tekstfelt som ikkje må oppdateres kontinuerlig (BTCs/s og knapper)
    void updateFields() {
        //Oppdaterer per sec
        perSecDisplay.setText(String.format("%.1f BTCs/s", main.getBitcoinsPerSec()));
        //Oppdaterer pris
        cursorUpgradeButton.setText(String.format("%.0f", main.getCursorPrice()));
        abakusButtonBuy.setText(String.format("%.0f", main.getAbakusPrice()));
        pascalineButtonBuy.setText(String.format("%.0f", main.getPascalinePrice()));
        eniacButtonBuy.setText(String.format("%.0f", main.getEniacPrice()));
        tradicButtonBuy.setText(String.format("%.0f", main.getTradicPrice()));
        appleIIButtonBuy.setText(String.format("%.0f", main.getAppleIIPrice()));
        commodore64ButtonBuy.setText(String.format("%.0f", main.getCommodore64Price()));
        appleMacintoshButtonBuy.setText(String.format("%.0f", main.getAppleMacintoshPrice()));
        //Oppdaterer count
        cursorLevelCount.setText(String.format("Level: %d", main.getCursorLevelCount()));
        abakusCountField.setText(String.format("%d", main.getAbakusCount()));
        pascalineCountField.setText(String.format("%d", main.getPascalineCount()));
        eniacCountField.setText(String.format("%d", main.getEniacCount()));
        tradicCountField.setText(String.format("%d", main.getTradicCount()));
        appleIICountField.setText(String.format("%d", main.getAppleIICount()));
        commodore64CountField.setText(String.format("%d", main.getCommodore64Count()));
        appleMacintoshCountField.setText(String.format("%d", main.getAppleMacintoshCount()));

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
                        counterOutput.setText(String.format("%.1f BTCs", main.getTotalBitcoins()));
                        disableButtons();
                    }
                });
            }
        };

        timer.schedule(updateRate, 0, 20);
    }

    //Getters og setters for instansen
    public static Main getObject() {
        return main;
    }

    public static void setObject(Main obj) {
        main = obj;
    }

    //Disabler knapper om du ikkje har råd
    void disableButtons() {
        boolean cursorDisable = main.getTotalBitcoins() < main.getCursorPrice();
        boolean abakusDisable = main.getTotalBitcoins() < main.getAbakusPrice();
        boolean pascalineDisable = main.getTotalBitcoins() < main.getPascalinePrice();
        boolean eniacDisable = main.getTotalBitcoins() < main.getEniacPrice();
        boolean tradicDisable = main.getTotalBitcoins() < main.getTradicPrice();
        boolean appleIIDisable = main.getTotalBitcoins() < main.getAppleIIPrice();
        boolean commodore64Disable = main.getTotalBitcoins() < main.getCommodore64Price();
        boolean appleMacintoshDisable = main.getTotalBitcoins() < main.getAppleMacintoshPrice();
        cursorUpgradeButton.setDisable(cursorDisable);
        abakusButtonBuy.setDisable(abakusDisable);
        pascalineButtonBuy.setDisable(pascalineDisable);
        eniacButtonBuy.setDisable(eniacDisable);
        tradicButtonBuy.setDisable(tradicDisable);
        appleIIButtonBuy.setDisable(appleIIDisable);
        commodore64ButtonBuy.setDisable(commodore64Disable);
        appleMacintoshButtonBuy.setDisable(appleMacintoshDisable);
    }

    //Permanent disabler knapper som ikkje er i bruk
    void disableTempButtons() {
        temp1.setDisable(true);
        temp2.setDisable(true);
        temp3.setDisable(true);
        temp4.setDisable(true);
        temp5.setDisable(true);
    }

    @FXML
    void startNewGame() {
        MainApp.startNewGame();
        initialize();
    }

}

