package pl.edu.pjwstk.masfinalproject.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {


    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.rents cr WHERE cr.id = :rentId")
    List<Car> findByRentId(int rentId);

    int countCarsByCarStatus(CarStatus carStatus);

    List<Car> getCarsByCarStatus(CarStatus carStatus);

    int countCarsByCarStatusAndId(CarStatus carStatus, int id);

    @Transactional
    @Modifying
    @Query("UPDATE Car c SET c.carStatus = :status WHERE c.id = :id")
    void updateCarStatus(@Param("status") CarStatus carStatus, @Param("id") int id);
}
