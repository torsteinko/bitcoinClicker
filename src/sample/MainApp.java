package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    //Lager saveHandler-objekt for å håndtere lagring
    SaveHandler saveHandler = new SaveHandler();

    //Startfunksjonen som blir kalt først
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Henter data fra fil så lenge denne ikkje er null
        //Ved null blir det kastet feilmelding om at fil er tom
        //Programmet starter da bare i default modus
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
            //Initialiserer vinduet
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Bitcoin Clicker");
            primaryStage.setScene(new Scene(root, 640, 480));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Blir kalt når applikasjonen lukkes ved kryss
    @Override
    public void stop() throws Exception{
        //Lagring

        //Sletter Controller som listener til Main
        //Viss ikkje vil controller objektet bli forsøkt serialisert, og dermed vil FXML-fil også bli det
        //FXML-filer kan ikkje bli serialisert, og vil forårsake problemer
        //Controller adder seg som listener på startup igjen i initialize()
        Main.getInstance().clearListeners();
        //Lagrer vha. SaveHandler
        saveHandler.writeToFile(Main.getInstance());
        //Går utav programmet
        //Dette trengs ettersom timerene vil fortsette å gå viss ikkje
        System.exit(0);
    }

    public static void startNewGame() {
        //Henter denne instansen
        Main oldMain = Main.getInstance();
        //Lager ein ny instans
        Main newMain = new Main();

        //Setter totalBitcoins lik det fra fil
        oldMain.setTotalBitcoins(newMain.getTotalBitcoins());
        //Setter alle shop-objekt lik det fra fil
        for (Shop s : oldMain.getShopList()) {
            s.updateFields(newMain.getShopObject(s.getName()));
        }

    }

    //Main
    public static void main(String[] args) {
        launch(args);

    }

}
