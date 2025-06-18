package pl.edu.pjwstk.masfinalproject.Model.Person.Employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pjwstk.masfinalproject.Model.Review;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Consultant extends Employee{


    @NotNull(message = "languages cannot be null")
    @NotEmpty(message = "languages cannot be empty")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "consultant_languages", joinColumns = @JoinColumn(name = "consultant_id"))
    private List<@NotNull(message = "language cannot be null") @NotEmpty(message = "language cannot be empty") String> languages;

    @NotNull(message = "position cannot be null")
    @NotEmpty(message = "position cannot be empty")
    private String position;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;

    public void addLanguage(String language) {
        if(languages == null || languages.isEmpty()) { throw new IllegalArgumentException("languages cannot be empty"); }
        languages.add(language);
    }
}
