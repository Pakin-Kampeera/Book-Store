package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {
    private Long id;

    @NotBlank(message = "Firstname is mandatory")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    private String lastName;

    private Set<Book> books;
}
