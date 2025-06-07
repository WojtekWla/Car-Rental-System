package pl.edu.pjwstk.masfinalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "telephone cannot be null")
    @Size(min=9, max = 9, message = "telephone number must have 9 digits")
    protected String telephone;

    @NotNull(message = "email cannot be null")
    @Size(min = 3, message = "email must contain at least 3 letters")
    @Email(message = "must be in email format")
    protected String email;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    private String location;

    @NotNull(message = "provided services cannot be null")
    @NotEmpty(message = "provided services cannot be empty")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mechanic_service", joinColumns = @JoinColumn(name = "mechanic_id"))
    private List<@NotNull(message = "provided service cannot be null") @NotEmpty(message = "provided service cannot be empty") String> providedServices;


    @OneToMany(mappedBy = "mechanic", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Service> services;
}
