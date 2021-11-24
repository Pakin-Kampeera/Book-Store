package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Write {
    @NotNull(message = "Author ID is required.")
    private String authorId;

    @NotEmpty(message = "Book ID is required.")
    private String bookId;
}
