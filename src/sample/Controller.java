package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller implements MainListener {

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
    @FXML
    Button saveButton;
    @FXML
    Button loadButton;

    Main main;

    //Initialiserer viktige
    @FXML
    void initialize() {
        main = Main.getInstance();
        //Oppdaterer variabler i main
        main.updateAtBuy();
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
        main.buyCursor();
        updateFields();
    }
    @FXML
    void buyAbakusAction() {
        main.buyShop("Abakus");
        updateFields();
    }
    @FXML
    void buyPascalineAction() {
        main.buyShop("Pascaline");
        updateFields();
    }
    @FXML
    void buyENIACAction() {
        main.buyShop("ENIAC");
        updateFields();
    }
    @FXML
    void buyTRADICAction() {
        main.buyShop("TRADIC");
        updateFields();
    }
    @FXML
    void buyAppleIIAction() {
        main.buyShop("Apple II");
        updateFields();
    }
    @FXML
    void buyCommodore64Action() {
        main.buyShop("Commodore 64");
        updateFields();
    }
    @FXML
    void buyAppleMacintoshAction() {
        main.buyShop("Apple Macintosh");
        updateFields();
    }

    //Oppdaterer tekstfelt som ikkje må oppdateres kontinuerlig (BTCs/s og knapper)
    void updateFields() {
        //Oppdaterer per sec
        satoshiPerSec.setText(String.format("%.1f Satoshi/s", main.getBitcoinsPerSec()));
        //Oppdaterer pris
        cursorUpgradeButton.setText(String.format("%.8f", main.getPrice("Cursor")/(10E7)));
        abakusButtonBuy.setText(String.format("%.8f", main.getPrice("Abakus")/(10E7)));
        pascalineButtonBuy.setText(String.format("%.8f", main.getPrice("Pascaline")/(10E7)));
        eniacButtonBuy.setText(String.format("%.8f", main.getPrice("ENIAC")/(10E7)));
        tradicButtonBuy.setText(String.format("%.8f", main.getPrice("TRADIC")/(10E7)));
        appleIIButtonBuy.setText(String.format("%.8f", main.getPrice("Apple II")/(10E7)));
        commodore64ButtonBuy.setText(String.format("%.8f", main.getPrice("Commodore 64")/(10E7)));
        appleMacintoshButtonBuy.setText(String.format("%.8f", main.getPrice("Apple Macintosh")/(10E7)));
        //Oppdaterer count
        cursorLevelCount.setText(String.format("Level: %d", main.getCount("Cursor")));
        abakusCountField.setText(String.format("%d", main.getCount("Abakus")));
        pascalineCountField.setText(String.format("%d", main.getCount("Pascaline")));
        eniacCountField.setText(String.format("%d", main.getCount("ENIAC")));
        tradicCountField.setText(String.format("%d", main.getCount("TRADIC")));
        appleIICountField.setText(String.format("%d", main.getCount("Apple II")));
        commodore64CountField.setText(String.format("%d", main.getCount("Commodore 64")));
        appleMacintoshCountField.setText(String.format("%d", main.getCount("Apple Macintosh")));
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

    //Disabler knapper om du ikkje har råd
    void disableButtons() {
        boolean cursorDisable = main.getTotalBitcoins() < main.getPrice("Cursor");
        boolean abakusDisable = main.getTotalBitcoins() < main.getPrice("Abakus");
        boolean pascalineDisable = main.getTotalBitcoins() < main.getPrice("Pascaline");
        boolean eniacDisable = main.getTotalBitcoins() < main.getPrice("ENIAC");
        boolean tradicDisable = main.getTotalBitcoins() < main.getPrice("TRADIC");
        boolean appleIIDisable = main.getTotalBitcoins() < main.getPrice("Apple II");
        boolean commodore64Disable = main.getTotalBitcoins() < main.getPrice("Commodore 64");
        boolean appleMacintoshDisable = main.getTotalBitcoins() < main.getPrice("Apple Macintosh");
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

    SaveHandler saveHandler = new SaveHandler();
    @FXML
    void loadGame() {
        try {
            if (saveHandler.readFromFile() != null) {
                Main newMain = saveHandler.readFromFile();
                Main oldMain = Main.getInstance();

                //Setter totalBitcoins lik det fra fil
                oldMain.setTotalBitcoins(newMain.getTotalBitcoins());
                //Setter alle shop-objekt lik det fra fil
                for (Shop s : oldMain.getShopList()) {
                    s.updateFields(newMain.getShopObject(s.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveGame() {
        //Lagring

        //Sletter Controller som listener til Main
        //Viss ikkje vil controller objektet bli forsøkt serialisert, og dermed vil FXML-fil også bli det
        //FXML-filer kan ikkje bli serialisert, og vil forårsake problemer
        //Controller adder seg som listener på startup igjen i initialize()
        Main.getInstance().clearListeners();
        //Lagrer vha. SaveHandler
        saveHandler.writeToFile(Main.getInstance());

        Main.getInstance().addListener(this);
    }

}

