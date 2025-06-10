package pl.edu.pjwstk.masfinalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "start date cannot be null")
    @FutureOrPresent(message = "start date must be in the future or now")
    private LocalDate startDate;


    @NotNull(message = "start date cannot be null")
    @Future(message = "end date must be in the future")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Person person;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Insurance insurance;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Car> cars;

    public double getPrice() {
        return cars
                .stream()
                .mapToDouble(car -> car.getRentPrice() * getDays())
                .sum();
    }

    public double getFinalPrice() {
        double price = getPrice();

        if(discount != null) {
            price -= price * (discount.getPercentage()/100.0);
        }
        if(insurance != null) {
            price = price + insurance.getPrice();
        }
        return price;
    }

    private long getDays() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
