import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        //MainManu mainManu = new MainManu();
        // to get rid of the annoying warnings from the MongoDB driver
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);

        while (true) {

            //mainManu.start();

        }
    }
}