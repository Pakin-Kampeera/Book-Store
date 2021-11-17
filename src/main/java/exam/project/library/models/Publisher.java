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
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
}
