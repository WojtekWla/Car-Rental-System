package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Rent;

import java.util.List;

public interface DiscountRepository extends CrudRepository<Discount, Integer> {

    @Query("SELECT d FROM Discount d")
    List<Discount> getAllDiscounts();

}
