package sample;

public class SingletonClass {
    private static final SingletonClass SINGLE_INSTANCE = new SingletonClass();
    private SingletonClass() {

    }
    public static SingletonClass getInstance() {
        return SINGLE_INSTANCE;
    }
}
