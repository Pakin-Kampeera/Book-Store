package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    private Long id;

    @NotNull(message = "Member ID is required.")
    private String memberId;

    @NotEmpty(message = "Book ID is required.")
    private Set<String> bookId;

    @NotNull(message = "Quantity is required.")
    private int quantity;

    private LocalDateTime date;

    private Double totalPrice;

    private Member member;

    private Set<Book> books;
}
