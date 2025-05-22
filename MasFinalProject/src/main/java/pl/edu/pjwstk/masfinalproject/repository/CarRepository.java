package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {


    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.rents cr WHERE cr.id = :rentId")
    List<Car> findByRentId(int rentId);
}
