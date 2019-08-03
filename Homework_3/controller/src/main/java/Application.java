
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

public class Application {
    @NotNull
    public static String fileName;
    public static final String LOW_LENGTH = "Library length is too low to hold all books";
    public static final String NO_CELL = "There is no cell #";
    public static final String NO_FREE_CELL = "Library does not contain availible cells";
    public static final String EMPTY_CELL = "You try to get book from empty cell";
    @NotNull
    public static int libraryLength;
    public static void main(@NotNull String args[]) throws LibraryException {
        if (args.length >= 2) {
            fileName = args[0];

            libraryLength = Integer.parseInt(args[1]);
        }


        try {
            if (fileName.isEmpty()) {
                throw new Exception("Path to file is empty");
            }
            if (libraryLength == 0) {
                throw new Exception("Library length is 0");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Injector injector = Guice.createInjector(new LibraryModule());
        Library library = injector.getInstance(LibraryFabric.class).createLibrary(libraryLength);
        library.getAll();
        try {
            library.addBook(new Book(new Author("AuthorX"), "Name1"));
            library.addBook(new Book(new Author("AuthorY"), "Name0"));
            library.getBook(22);
            library.getAll();
            library.getBook(22);
        }
        catch (LibraryException e) {
            System.out.println(e.getMessage());
        }



    }
}
