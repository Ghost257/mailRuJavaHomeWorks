import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class LoggerModule extends AbstractModule {
    @Override
     protected void configure() {
        bind(Application.class);
        bind(Logger.class).annotatedWith(ToConsole.class).to(ConsoleLogger.class);
        bind(Logger.class).annotatedWith(ToFile.class).to(FileLogger.class);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(ConsoleLoggingMethod.class), new Interceptor());
    }
}
