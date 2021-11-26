package exam.project.library.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Transaction {
    private Long id;

    @NotNull(message = "Member ID is required.")
    private Long memberId;

    @NotNull(message = "Book ID is required.")
    private Long bookId;

    @NotNull(message = "ISBN is required.")
    private String ISBN;

    @NotNull(message = "Price is required.")
    private int price;

    @NotNull(message = "Quantity is required.")
    private int quantity;
}
