package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller implements MainListener {

    //Initialiserer som static for å kunne aksesere i MainApp
    //Dette sånn at lagring og gjenoppretting kan trigges fra start() og stop()
    private static Main main = new Main();

    //FXML referanser til sample
    @FXML
    Text counterOutput;
    @FXML
    Text satoshiTotalCount;
    @FXML
    Text satoshiPerSec;
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
        main.addListener(this);
        updateFields();
        disableTempButtons();
    }

    //Funksjon som knyttes til klikk på bitcoin i GUI
    @FXML
    void countAction() {
        main.bitcoinOnClick();
        counterOutput.setText(String.format("%.8f BTCs", Math.floor(main.getTotalBitcoins())/(10E7)));
        satoshiTotalCount.setText(String.format("%.1f Satoshi", main.getTotalBitcoins()));
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
        updateFields();
    }

    //Oppdaterer tekstfelt som ikkje må oppdateres kontinuerlig (BTCs/s og knapper)
    void updateFields() {
        //Oppdaterer per sec
        satoshiPerSec.setText(String.format("%.1f Satoshi/s", main.getBitcoinsPerSec()));
        //Oppdaterer pris
        cursorUpgradeButton.setText(String.format("%.8f", main.getCursorPrice()/(10E7)));
        abakusButtonBuy.setText(String.format("%.8f", main.getAbakusPrice()/(10E7)));
        pascalineButtonBuy.setText(String.format("%.8f", main.getPascalinePrice()/(10E7)));
        eniacButtonBuy.setText(String.format("%.8f", main.getEniacPrice()/(10E7)));
        tradicButtonBuy.setText(String.format("%.8f", main.getTradicPrice()/(10E7)));
        appleIIButtonBuy.setText(String.format("%.8f", main.getAppleIIPrice()/(10E7)));
        commodore64ButtonBuy.setText(String.format("%.8f", main.getCommodore64Price()/(10E7)));
        appleMacintoshButtonBuy.setText(String.format("%.8f", main.getAppleMacintoshPrice()/(10E7)));
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

    //Observerer Main, endrer GUI når Main endrer seg
    public void mainChanged(double totalBitcoins) {
        //Endrer GUI når Main adder
        counterOutput.setText(String.format("%.8f BTCs", (Math.floor(totalBitcoins))/(10E7)));
        satoshiTotalCount.setText(String.format("%.1f Satoshi", totalBitcoins));

        //Kaller på denne her, siden mainChanged blir kallet på såpass regelmessig.
        //Kunne brukt observatør-observert for hver knapp, men dette er mykje arbeid for en relativt smal gevinst.
        disableButtons();
    }

    public static void clearMainListeners() {
        main.clearListeners();
    }

    //Getters og setters for instansen (Til lagring i MainApp)
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

