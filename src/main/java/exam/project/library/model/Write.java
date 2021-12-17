package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Write implements Serializable {
    @NotNull(message = "Author ID is required.")
    private String authorId;

    @NotEmpty(message = "Book ID is required.")
    private String bookId;
}
