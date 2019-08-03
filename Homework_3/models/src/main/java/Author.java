import lombok.Data;
import lombok.NonNull;

import java.util.Date;
@Data
public class Author {
    @NonNull
    private String name;
    public Author(@NonNull String name) {
        this.name = name;
    }
}
