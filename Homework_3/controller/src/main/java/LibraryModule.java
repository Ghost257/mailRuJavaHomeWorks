import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LibraryFabric.class).to(FileLibraryFabric.class);
    }


}
