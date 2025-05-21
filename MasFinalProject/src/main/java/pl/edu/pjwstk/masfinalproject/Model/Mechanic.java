package pl.edu.pjwstk.masfinalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
