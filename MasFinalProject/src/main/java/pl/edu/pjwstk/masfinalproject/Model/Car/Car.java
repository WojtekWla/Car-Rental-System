package pl.edu.pjwstk.masfinalproject.Model.Car;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarType;
import pl.edu.pjwstk.masfinalproject.Model.Rent;
import pl.edu.pjwstk.masfinalproject.Model.Review;
import pl.edu.pjwstk.masfinalproject.Model.Service;

import java.util.EnumSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Positive(message = "maximum distance to service must be positive")
    public static int maximumDistanceToService = 10000;

    @NotNull(message = "brand cannot be null")
    @NotBlank(message = "brand cannot be empty")
    private String brand;

    @NotNull(message = "model cannot be null")
    @NotBlank(message = "model cannot be empty")
    private String model;

    @Positive(message = "rent price cannot be negative")
    private int rentPrice;

    @NotNull(message = "car status cannot be null")
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @NotNull(message = "car type cannot be null")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Positive(message = "travelled distance cannot be negative")
    private int travelledDistanceToService;

    @NotNull(message = "engines cannot be null")
    @Size(min = 1, max = 2, message = "car has to have at least one engine and up to two engines (one oil and one electric)")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "car_engines", joinColumns = @JoinColumn(name = "car_id"))
    @Enumerated(EnumType.STRING)
    private Set<EngineType> engines;

    //electric engine
    @Nullable
    private Integer maximumRange;

    //oil engine
    @Nullable
    private String benzineType;

    @Nullable
    private String manufacturerName;

    @ManyToMany(mappedBy = "cars", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Rent> rents;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Service> services;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;

    public int getMaximumRange() {
        if (engines.contains(EngineType.ElectricEngine)) {
            return maximumRange;
        }
        throw new IllegalStateException("Car doesn't have electric engine");
    }

    public void setMaximumRange(int maximumRange) {
        if (engines.contains(EngineType.ElectricEngine)) {
            this.maximumRange = maximumRange;
        } else {
            System.out.println("Car doesn't have electric engine");
        }
    }

    public String getBenzineType() {
        if (engines.contains(EngineType.OilEngine)) {
            return benzineType;
        }
        throw new IllegalStateException("Car doesn't have oil engine");
    }

    public void setBenzineType(String oilType) {
        if (engines.contains(EngineType.OilEngine)) {
            this.benzineType = oilType;
        } else {
            System.out.println("Car doesn't have oil engine");
        }
    }

    public String getManufacturerName() {
        if (engines.contains(EngineType.OilEngine)) {
            return manufacturerName;
        }
        throw new IllegalStateException("Car doesn't have oil engine");
    }

    public void setManufacturerName(String manufacturerName) {
        if (engines.contains(EngineType.OilEngine)) {
            this.manufacturerName = manufacturerName;
        } else {
            System.out.println("Car doesn't have oil engine");
        }
    }

    @AssertTrue(message = "If the car has an oil engine, benzineType cannot be blank")
    private boolean isBenzineTypeValid() {
        if (engines != null && engines.contains(EngineType.OilEngine)) {
            return benzineType != null && !benzineType.isBlank();
        }
        return true;
    }

    @AssertTrue(message = "If the car has an oil engine, manufacturerName cannot be blank")
    private boolean isManufacturerNameValid() {
        if (engines != null && engines.contains(EngineType.OilEngine)) {
            return manufacturerName != null && !manufacturerName.isBlank();
        }
        return true;
    }
}

