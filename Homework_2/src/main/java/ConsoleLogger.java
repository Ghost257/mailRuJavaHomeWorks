public class ConsoleLogger implements Logger {
    @Override
    @ConsoleLoggingMethod
    public void log(int counter, String tag, String message) {
        System.out.println(counter + " " + message);
    }
}
