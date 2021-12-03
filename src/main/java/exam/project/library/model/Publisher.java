package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Publisher implements Serializable {
    @JsonProperty("id")
    private Long publisherId;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Street is required.")
    private String street;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "Zip is required.")
    private String zip;

    private Set<Book> books;
}
