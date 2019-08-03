import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public interface LibraryFabric {
    Collection<Book> books();
    Library createLibrary(int length) throws LibraryException;
}
