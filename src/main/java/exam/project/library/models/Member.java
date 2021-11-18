package exam.project.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private Set<Book> books;
}
