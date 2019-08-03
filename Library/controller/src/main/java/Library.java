import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private ArrayList<Book> books;
    private HashMap<String, ArrayList<Book>> authors = new HashMap<>();

    public void addBook(Author author, Book book) {
        if (authors.containsKey(author.getName())) {
            authors.get(author.getName()).add(book);
        }
        else {
            books = new ArrayList<>();
            books.add(book);
            authors.put(author.getName(), books);
        }
    }
    public ArrayList<Book> getAuthorsBooks(String authorName) {

        if (authors.containsKey(authorName)) {
            return authors.get(authorName);
        }
        else {
            return new ArrayList<Book>();
        }
    }
}
