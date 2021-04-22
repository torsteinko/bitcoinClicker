package sample;

public interface SaveFile {

    void writeToFile(Main main);
    Main readFromFile();
    boolean createNewDataFile();

}
