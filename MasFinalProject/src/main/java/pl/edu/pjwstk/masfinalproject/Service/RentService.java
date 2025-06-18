package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.CarDTO;
import pl.edu.pjwstk.masfinalproject.DTO.RentDTO;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Model.Rent;
import pl.edu.pjwstk.masfinalproject.repository.RentRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final CustomerService customerService;
    private final CarService carService;


    public Set<Rent> getAllCarsRents(int carId) {
        return rentRepository.findRentByCarsContainingCar(carId);
    }

    public Rent getRent(int rentId) {
        Optional<Rent> rent = rentRepository.findById(rentId);
        return rent.orElse(null);
    }


    @Transactional
    public int saveNewRent(Rent rent) {
        if(rent.getPerson() instanceof Customer) {
            customerService.addPoints((Customer) rent.getPerson(), rent.getFinalPrice());
        }

        for (Car car : rent.getCars()) {
            carService.changeCarStateTo(CarStatus.RENTED, car.getId());
        }
        Rent result = rentRepository.save(rent);
        return result.getId();
    }

    public void removePersonRents(Person person) {
        rentRepository.deleteByPerson(person);
    }

    @Modifying
    public void updateRent(Rent rent) {
        rentRepository.save(rent);
    }

    public void removeRent(Rent rent) {
        rentRepository.delete(rent);
    }

}
