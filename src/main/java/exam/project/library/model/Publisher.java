package exam.project.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Publisher {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String zip;
    private Set<Book> books;
}
