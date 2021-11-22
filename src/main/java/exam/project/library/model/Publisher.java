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
public class Publisher {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Street is mandatory")
    private String street;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Zip is mandatory")
    private String zip;

    private Set<Book> books;
}
