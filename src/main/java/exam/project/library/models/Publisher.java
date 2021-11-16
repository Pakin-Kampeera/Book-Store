package exam.project.library.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Publisher {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String email;
}
