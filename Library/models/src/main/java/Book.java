
import lombok.Data;

import java.util.Date;
@Data
public class Book {
    private Author author;
    private String title;
    private String isbn;
    private Date publicationYear;
    private String publishingHouse;
    public Book (Author author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }
}
