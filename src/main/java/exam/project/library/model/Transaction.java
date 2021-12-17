package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction implements Serializable {
    @JsonProperty("id")
    private Long transactionId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Member ID is required.")
    private Integer memberId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Book ID is required.")
    private Integer bookId;

    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    private LocalDateTime date;

    private Double totalPrice;

    private Member member;

    private Book book;
}
