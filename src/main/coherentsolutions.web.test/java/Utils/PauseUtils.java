package Utils;

public class PauseUtils {

    public static void pause(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }
}

