package pl.edu.pjwstk.masfinalproject.Model.Car;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarType;
import pl.edu.pjwstk.masfinalproject.Model.Rent;
import pl.edu.pjwstk.masfinalproject.Model.Review;
import pl.edu.pjwstk.masfinalproject.Model.Service;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private static int maximumDistanceToService = 1000;

    @NotNull(message = "brand cannot be null")
    @NotBlank(message = "brand cannot be empty")
    private String brand;

    @NotNull(message = "model cannot be null")
    @NotBlank(message = "model cannot be empty")
    private String model;

    @Positive(message = "rent price cannot be negative")
    private int rentPrice;

    @NotNull(message = "car status cannot be null")
    private CarStatus carStatus;

    @NotNull(message = "car type cannot be null")
    private CarType carType;

    @Positive(message = "travelled distance cannot be negative")
    private int travelledDistance;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Rent> rents;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Service> services;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;
}
