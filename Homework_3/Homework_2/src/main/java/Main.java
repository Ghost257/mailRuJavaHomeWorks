import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class Main {

    //Arguments: mode tag
    //Mode: console - out to console; file - to file; combine or another word - to console and to file
    public static void main(@NotNull String[] args) {
        String mode = "";
        String tag = "";
        if (args.length == 2) {
            mode = args[0];
            tag = args[1];
        }
        @NotNull
        Injector injector = Guice.createInjector(new LoggerModule());
        Application app = injector.getInstance(Application.class);
        app.setMode(mode);
        app.setTag(tag);
        app.waitForInput();

    }


}
