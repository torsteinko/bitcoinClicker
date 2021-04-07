package sample;

import java.io.*;

public class SaveHandler {

    //Funksjon som tar inn eit Main objekt og skriver til fil
    public static void writeToFile(Main main) {
        try {
            FileOutputStream f = new FileOutputStream(("src/sample/data.ser"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(main);
            o.flush();

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

    //Funksjon som returnerer et Main objekt fra fil
    public static Main readFromFile() {
        try {
            FileInputStream f = new FileInputStream(("src/sample/data.ser"));
            ObjectInputStream o = new ObjectInputStream(f);

            Main main = (Main) o.readObject();

            o.close();
            f.close();

            return main;

        } catch (FileNotFoundException e) {
        System.out.println("File not found");
        } catch (IOException e) {
        System.out.println("Fil tom - Kjører default oppsett");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void newGame() {
        try {
            FileOutputStream f = new FileOutputStream(("src/sample/data.ser"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject((Main) new Main());
            o.flush();

            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

}

