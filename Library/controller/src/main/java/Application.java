import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.lang.System.*;

public class Application {

    public static void main (String args[]) {
        String authorName;
        LibraryFabric libraryFabric;
        ArrayList<Book> authorsBooks;
        authorName = args[0];
        for (int i = 1; i < args.length; i++) {
            authorName = authorName + " " + args[i];
        }
        libraryFabric = new LibraryFabric();
        Library library = libraryFabric.getLibrary();
        System.out.println(authorName);
        authorsBooks = library.getAuthorsBooks(authorName);
        Gson gson = new Gson();
        String json;
        if (authorsBooks.isEmpty()) {
            System.out.println("Library does not contain books of this author");
        }
        else {
            System.out.println("Books written by " + authorName + ":");
            for (int i = 0; i < authorsBooks.size(); i++) {
                json = gson.toJson(authorsBooks.get(i));
                System.out.println(json);
            }
        }
    }
}
