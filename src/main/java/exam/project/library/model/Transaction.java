package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"memberId", "bookId"})
public class Transaction {
    private Long id;

    @NotNull(message = "Member ID is required.")
    private int memberId;

    @NotNull(message = "Book ID is required.")
    private int bookId;

    @NotNull(message = "Quantity is required.")
    private int quantity;

    private LocalDateTime date;

    private Double totalPrice;

    private Member member;

    private Book book;
}
