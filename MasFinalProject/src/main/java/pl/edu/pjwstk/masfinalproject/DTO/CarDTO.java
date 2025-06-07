package pl.edu.pjwstk.masfinalproject.DTO;

import lombok.*;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDTO {
    private int id;
    private String brand;
    private String model;
    private CarType carType;
    private int rentPrice;
    private CarStatus carStatus;
}
