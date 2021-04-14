package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    //Startfunksjonen som blir kalt først
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Henter data fra fil så lenge denne ikkje er null
        //Ved null blir det kastet feilmelding om at fil er tom
        //Programmet starter da bare i default modus
        try {
            if (SaveHandler.readFromFile() != null) {
                Controller.setObject((Main) SaveHandler.readFromFile());
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
        Controller.clearMainListeners();
        //Lagrer vha. SaveHandler
        SaveHandler.writeToFile(Controller.getObject());
        //Går utav programmet
        //Dette trengs ettersom timerene vil fortsette å gå viss ikkje
        System.exit(0);
    }

    public static void startNewGame() {
        //Sletter listeners (Controller) til objektet
        Controller.clearMainListeners();
        //Lager ein ny instans av Main-objektet
        Controller.setObject(new Main());
    }

    //Main
    public static void main(String[] args) {
        launch(args);
    }

}
