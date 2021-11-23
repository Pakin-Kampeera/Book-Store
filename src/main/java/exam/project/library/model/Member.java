package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    private Long id;

    @NotBlank(message = "Firstname is required.")
    private String firstName;

    @NotBlank(message = "Lastname is required.")
    private String lastName;

    @NotBlank(message = "Telephone is required.")
    private String telephone;

    private Set<Book> books;
}
