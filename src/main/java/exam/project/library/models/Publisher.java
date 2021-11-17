package exam.project.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zip;
}