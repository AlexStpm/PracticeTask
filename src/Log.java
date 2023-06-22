import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static boolean added;
    static FileHandler fh;
    private Log(){

    }
    public static void addFileHandler() {
        if (!added) {
            try {
                fh = new FileHandler("LogFile.log",true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                Logger.getLogger("").addHandler(fh);
                added = true;
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
