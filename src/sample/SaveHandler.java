package sample;

import java.io.*;

public class SaveHandler {

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

    public static Object readFromFile() {
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
        System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

