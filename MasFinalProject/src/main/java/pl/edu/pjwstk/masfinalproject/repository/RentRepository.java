package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.pjwstk.masfinalproject.Model.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends CrudRepository<Rent, Integer> {

    @Query("SELECT r FROM Rent r") // associations will not be fetched unless accessed
    List<Rent> findAllRents();

}
