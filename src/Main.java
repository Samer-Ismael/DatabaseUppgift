import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        MainManu mainManu = new MainManu();
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        while (true) {

            mainManu.start();

        }
    }
}