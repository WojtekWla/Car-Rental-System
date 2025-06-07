package pl.edu.pjwstk.masfinalproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class InsuranceDTO {
    private int id;
    private String name;
    private String description;
    private int price;
}
