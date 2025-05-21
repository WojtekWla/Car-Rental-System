package pl.edu.pjwstk.masfinalproject.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "type of discount cannot be null")
    @NotBlank(message = "type of discount cannot be empty")
    private String typeOfDiscount;

    @NotNull(message = "description cannot be null")
    @NotBlank(message = "description cannot be empty")
    private String description;

    @Min(value = 10, message = "discount has to be at least 10%")
    @Max(value = 90, message = "discount can be up to 90%")
    private int percentage;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Rent> rents;
}
