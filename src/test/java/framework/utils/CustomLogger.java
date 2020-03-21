package framework.utils;

public class CustomLogger {
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger("API test");

    public static void makeStepLog(int step, String massage) {
        String customLog = step + " - " + massage;
        log.info(customLog);
    }

    public static void makeErrorLog(String massage) {
        String customLog = "Error: " + massage;
        log.info(customLog);
        throw new IllegalStateException();
    }

    public static void makeLog(String massage) {
        log.info(massage);
    }
}
