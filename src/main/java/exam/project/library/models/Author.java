package exam.project.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Book> books;
}
