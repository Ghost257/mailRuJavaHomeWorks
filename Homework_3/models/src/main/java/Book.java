import lombok.Data;
import lombok.NonNull;

@Data
public class Book {
    @NonNull
    private Author author;
    @NonNull
    private String name;
    public Book (@NonNull Author author, @NonNull String name) {
        this.author = author;
        this.name = name;
    }
}
