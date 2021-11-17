package exam.project.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Set<Book> book;
}
