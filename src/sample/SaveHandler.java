package sample;

import java.io.*;

public class SaveHandler {

    public static void writeToFile(Object object) {
        try {
            FileOutputStream f = new FileOutputStream(new File("src/sample/data.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(object);

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

    public static Object readFromFile(String filename) {
        try {
            FileInputStream f = new FileInputStream((filename));
            ObjectInputStream o = new ObjectInputStream(f);

            Object obj = o.readObject();

            o.close();
            f.close();

            return obj;

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

