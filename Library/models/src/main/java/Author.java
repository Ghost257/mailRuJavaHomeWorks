import lombok.Data;
import java.util.Date;
@Data
public class Author {
    private String name;
    private Date birthday;
    private String country;
    private String language;
    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
