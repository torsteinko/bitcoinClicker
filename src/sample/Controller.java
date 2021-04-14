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
        //Oppdaterer variabler i main
        main.updateAtBuy();
        //Starter timer i main
        main.timerUpdate();
        //Legger controller til som listener
        main.addListener(this);
        //Oppdaterer GUI
        updateFields();
        disableTempButtons();
    }

    //Funksjon som knyttes til klikk på bitcoin i GUI
    @FXML
    void countAction() {
        main.bitcoinOnClick();
    }

    //Kjøp funksjoner som knyttes til knapper i GUI
    @FXML
    void buyCursorAction() {
        main.buyCursor(main.cursor);
        updateFields();
    }
    @FXML
    void buyAbakusAction() {
        main.buyShop(main.abakus);
        updateFields();
    }
    @FXML
    void buyPascalineAction() {
        main.buyShop(main.pascaline);
        updateFields();
    }
    @FXML
    void buyENIACAction() {
        main.buyShop(main.eniac);
        updateFields();
    }
    @FXML
    void buyTRADICAction() {
        main.buyShop(main.tradic);
        updateFields();
    }
    @FXML
    void buyAppleIIAction() {
        main.buyShop(main.appleII);
        updateFields();
    }
    @FXML
    void buyCommodore64Action() {
        main.buyShop(main.commodore64);
        updateFields();
    }
    @FXML
    void buyAppleMacintoshAction() {
        main.buyShop(main.appleMacintosh);
        updateFields();
    }

    //Oppdaterer tekstfelt som ikkje må oppdateres kontinuerlig (BTCs/s og knapper)
    void updateFields() {
        //Oppdaterer per sec
        satoshiPerSec.setText(String.format("%.1f Satoshi/s", main.getBitcoinsPerSec()));
        //Oppdaterer pris
        cursorUpgradeButton.setText(String.format("%.8f", main.getPrice(main.cursor)/(10E7)));
        abakusButtonBuy.setText(String.format("%.8f", main.getPrice(main.abakus)/(10E7)));
        pascalineButtonBuy.setText(String.format("%.8f", main.getPrice(main.pascaline)/(10E7)));
        eniacButtonBuy.setText(String.format("%.8f", main.getPrice(main.eniac)/(10E7)));
        tradicButtonBuy.setText(String.format("%.8f", main.getPrice(main.tradic)/(10E7)));
        appleIIButtonBuy.setText(String.format("%.8f", main.getPrice(main.appleII)/(10E7)));
        commodore64ButtonBuy.setText(String.format("%.8f", main.getPrice(main.commodore64)/(10E7)));
        appleMacintoshButtonBuy.setText(String.format("%.8f", main.getPrice(main.appleMacintosh)/(10E7)));
        //Oppdaterer count
        cursorLevelCount.setText(String.format("Level: %d", main.getCount(main.cursor)));
        abakusCountField.setText(String.format("%d", main.getCount(main.abakus)));
        pascalineCountField.setText(String.format("%d", main.getCount(main.pascaline)));
        eniacCountField.setText(String.format("%d", main.getCount(main.eniac)));
        tradicCountField.setText(String.format("%d", main.getCount(main.tradic)));
        appleIICountField.setText(String.format("%d", main.getCount(main.appleII)));
        commodore64CountField.setText(String.format("%d", main.getCount(main.commodore64)));
        appleMacintoshCountField.setText(String.format("%d", main.getCount(main.appleMacintosh)));

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

    //For å fjerne alle listeners
    //Static slik at MainApp skal kunne kalle den
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
        boolean cursorDisable = main.getTotalBitcoins() < main.getPrice(main.cursor);
        boolean abakusDisable = main.getTotalBitcoins() < main.getPrice(main.abakus);
        boolean pascalineDisable = main.getTotalBitcoins() < main.getPrice(main.pascaline);
        boolean eniacDisable = main.getTotalBitcoins() < main.getPrice(main.eniac);
        boolean tradicDisable = main.getTotalBitcoins() < main.getPrice(main.tradic);
        boolean appleIIDisable = main.getTotalBitcoins() < main.getPrice(main.appleII);
        boolean commodore64Disable = main.getTotalBitcoins() < main.getPrice(main.commodore64);
        boolean appleMacintoshDisable = main.getTotalBitcoins() < main.getPrice(main.appleMacintosh);
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

    //Starter nytt spill
    @FXML
    void startNewGame() {
        MainApp.startNewGame();
        initialize();
    }

}

