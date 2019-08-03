import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class FileLibraryFabric implements LibraryFabric {
    @NotNull
    private final String fileName = Application.fileName;
    @NotNull
    private static final Type listBooksType = new TypeToken<ArrayList<Book>>() {
    }.getType();


    @Override
    @NotNull
    public Collection<Book> books() {
        try {
            System.out.println(Application.fileName);
           return new Gson().fromJson(new BufferedReader(new FileReader(fileName)), listBooksType);

        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public Library createLibrary(@NotNull int length) throws LibraryException {
        ArrayList<Book> bookCollection = new ArrayList<>(this.books());
        return new Library(length, bookCollection);

    }

}