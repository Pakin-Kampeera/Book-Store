package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    private Long id;

    @NotBlank(message = "Firstname is mandatory")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    private String lastName;

    @NotBlank(message = "Telephone is mandatory")
    private String telephone;

    private Set<Book> books;
}
