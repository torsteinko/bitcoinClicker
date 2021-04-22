package sample;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class SaveHandler implements SaveFile {

    //Funksjon som tar inn eit Main objekt og skriver til fil
    public void writeToFile(Main main) {
        if (createNewDataFile()) {
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
                e.printStackTrace();
                System.out.println("Error initializing stream");
            }
        }
    }

    //Funksjon som returnerer et Main objekt fra fil
    public Main readFromFile() {
        try {
            FileInputStream f = new FileInputStream(("src/sample/data.ser"));
            ObjectInputStream o = new ObjectInputStream(f);

            Main main = (Main) o.readObject();

            o.close();
            f.close();

            return main;

        } catch (FileNotFoundException e) {
            System.out.println("File not found - Fortsetter som normalt");
        } catch (IOException e) {
            System.out.println("Fil tom - Kjører default oppsett");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createNewDataFile() {
        try {
            File f = new File("src/sample/data.ser");
            f.createNewFile();
            return true;
        } catch (FileAlreadyExistsException e) {
            System.out.println("Fil finnes allerede, fortsetter som normalt.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

