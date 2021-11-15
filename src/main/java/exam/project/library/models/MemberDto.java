package exam.project.library.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate memberDate;
}
