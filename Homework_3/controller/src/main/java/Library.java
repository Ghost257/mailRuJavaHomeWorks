import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Library {
    @NotNull
    private List<Book> books = new ArrayList<Book>();
    private int libraryLength;
    public Library(@NotNull int length, @NotNull  ArrayList<Book> books) throws LibraryException {
        this.libraryLength = length;
        if (libraryLength < books.size()) {
//            try {
                for (int i = 0; i < libraryLength; i++) {
                    this.books.add(books.get(i));
                }
                throw new LibraryException(Application.LOW_LENGTH);
//            } catch (LibraryException e) {
//                System.out.println(e.getMessage());
//            }
        }
        else {
            for (int i = 0; i < books.size(); i++) {
                this.books.add(books.get(i));
            }
            for (int i = books.size(); i < libraryLength; i++) {
                this.books.add(null);
            }
        }
        }

    public void addBook(@NotNull Book book) throws LibraryException {
        for (int i = 0; i < libraryLength; i++) {
            if (books.get(i) == null) {
                books.set(i, book);
                System.out.println("Book successfully added");
                return;
            }
        }
        if (books.size() >= libraryLength) {
//             try {
                    throw new LibraryException(Application.NO_FREE_CELL);
//                } catch (LibraryException e) {
//                    System.out.println(e.getMessage());
//                }
            }
//        else {
//            books.add(book);
//            System.out.println("Book successfully added");
//        }
        }


    public Book getBook(@NotNull int cellId) throws LibraryException {
        Book temp;
        if (cellId <= (libraryLength - 1)) {
            if (books.get(cellId) != null) {
                temp = books.get(cellId);
                books.set(cellId, null);
                Gson gson = new Gson();
                String json = gson.toJson(temp);
                System.out.println("Cell #" + cellId + ": " + json);
                return temp;
            }
            else {
                throw new LibraryException(Application.EMPTY_CELL);
            }
        }
        else {
            throw new LibraryException(Application.NO_CELL + cellId);
        }
    }
    public void getAll() {
        Gson gson = new Gson();
        String json;
        System.out.println("Library contains:");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i) != null) {
                json = gson.toJson(books.get(i));
                System.out.println(json);
            }
            else {
                System.out.println("Empty cell");
            }
             }
    }
}
