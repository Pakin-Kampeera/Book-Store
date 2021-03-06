package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book implements Serializable {
    @JsonProperty("id")
    private Long bookId;

    @NotBlank(message = "Title is required.")
    private String title;

    @NotNull(message = "Price is required.")
    private Double price;

    @NotBlank(message = "ISBN is required.")
    private String ISBN;

    @NotBlank(message = "Publisher is required.")
    private String publisherId;

    @NotEmpty(message = "Author ID is required.")
    private Set<String> authorId;

    private Publisher publisher;

    private Set<Author> authors;
}
