import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Application {
    public int logCounter;
    @Inject
    @ToFile
    @NotNull

    private Logger toFileLogger;
    @Inject
    @ToConsole
    @NotNull

    private Logger toConsoleLogger;

    private String tag;
    private String mode;
    public String nextLine;
    public String message;
    public void waitForInput() {
        try (Scanner lineScanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Type empty line to exit.");
            while (true) {
                if (lineScanner.hasNextLine()) {
                    nextLine = lineScanner.nextLine();
                    if (!nextLine.isEmpty()) {
                        handle(new Scanner(nextLine));
                    }
                    else {
                        break;
                    }
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
        }

    }
    public void handle(@NotNull Scanner scanner) {
        message = "";
        while (scanner.hasNext()) {
            message += scanner.next();
            message += " ";
        }

        writeToLog(tag, message.trim());
    }
    public void writeToLog(String tag,  String message) {
        switch (mode) {
            case "console":
                toConsoleLogger.log(logCounter, tag, message);
                logCounter++;
                break;
            case "file":
                toFileLogger.log(logCounter, tag, message);
                logCounter++;
                break;
            default:
                toConsoleLogger.log(logCounter, tag, message);
                logCounter++;
                toFileLogger.log(logCounter, tag, message);
                logCounter++;
                break;
        }

    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
