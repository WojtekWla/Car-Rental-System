package pl.edu.pjwstk.masfinalproject.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentDTO {
    private int id;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private double price;
    private double finalPrice;
    private Set<CarDTO> rentedCars;
    private DiscountDTO discount;
    private InsuranceDTO insurance;
    private PersonDTO person;
}
