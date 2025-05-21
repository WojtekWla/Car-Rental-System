package pl.edu.pjwstk.masfinalproject.Model.Car;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class OilEngine extends Car{

    @NotNull(message = "oil type cannot be null")
    @NotBlank(message = "oil type cannot be empty")
    private String oilType;

    @NotNull(message = "manufacturer name cannot be null")
    @NotBlank(message = "manufacturer name cannot be empty")
    private String manufacturerName;
}
