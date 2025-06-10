package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.pjwstk.masfinalproject.Model.Rent;

import java.util.List;
import java.util.Set;

public interface RentRepository extends CrudRepository<Rent, Integer> {

    @Query("SELECT r FROM Rent r")
    List<Rent> findAllRents();

    @Query("SELECT r FROM Rent r JOIN r.cars c WHERE c.id = :carId ORDER BY r.startDate DESC")
    Set<Rent> findRentByCarsContainingCar(@Param("carId") int carId);
}
