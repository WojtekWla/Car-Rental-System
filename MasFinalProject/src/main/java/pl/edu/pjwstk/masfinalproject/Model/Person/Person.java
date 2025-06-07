package pl.edu.pjwstk.masfinalproject.Model.Person;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.masfinalproject.Model.Rent;
import pl.edu.pjwstk.masfinalproject.Model.Review;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) //to extract subclass we use join
@SuperBuilder
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    protected String name;

    @NotNull(message = "surname cannot be null")
    @NotBlank(message = "name cannot be empty")
    protected String surname;

    @NotNull(message = "pesel cannot be null")
    @Size(min = 11, max = 11, message = "pesel has to have 11 digits")
    protected String pesel;

    @NotNull(message = "telephone cannot be null")
    @Size(min=9, max = 9, message = "telephone number must have 9 digits")
    protected String telephone;

    @NotNull(message = "email cannot be null")
    @Size(min = 3, message = "email must contain at least 3 letters")
    @Email(message = "must be in email format")
    protected String email;

    @NotNull(message = "birth date cannot be null")
    @Past(message = "birth date must be in the past")
    protected LocalDate birthDate;

    @NotNull(message = "gender cannot be null")
    @NotBlank(message = "gender cannot be empty")
    protected String gender;

    @NotNull(message = "residentialAddress cannot be null")
    @NotBlank(message = "residential address cannot be empty")
    protected String residentialAddress;

    //associations

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Rent> rents;
}
