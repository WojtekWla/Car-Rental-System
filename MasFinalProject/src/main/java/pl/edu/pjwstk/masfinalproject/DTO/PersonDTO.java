package pl.edu.pjwstk.masfinalproject.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDTO {
    private Integer id;
    private String name;
    private String surname;
    private String pesel;
}
