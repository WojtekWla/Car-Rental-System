package pl.edu.pjwstk.masfinalproject.DTO;

import lombok.*;
import org.apache.catalina.Engine;
import pl.edu.pjwstk.masfinalproject.Model.Car.EngineType;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarType;

import java.util.List;
import java.util.Set;

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
    private List<String> engineTypes;
    private String maximumRange;
    private String benzineType;
    private String manufacturerName;
}
