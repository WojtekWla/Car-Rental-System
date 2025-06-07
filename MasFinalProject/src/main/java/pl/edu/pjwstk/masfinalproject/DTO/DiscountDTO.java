package pl.edu.pjwstk.masfinalproject.DTO;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DiscountDTO {
    private int id;
    private String typeOfDiscount;
    private String description;
    private int percentage;


}
