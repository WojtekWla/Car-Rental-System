package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.CarDTO;
import pl.edu.pjwstk.masfinalproject.DTO.RentDTO;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.Model.Rent;
import pl.edu.pjwstk.masfinalproject.repository.RentRepository;

import java.util.*;

@Service
public class RentService {
    private RentRepository rentRepository;
    private PersonService personService;
    private DiscountService discountService;
    private InsuranceService insuranceService;
    private CarService carService;
    public RentService(RentRepository rentRepository, PersonService personService, DiscountService discountService, InsuranceService insuranceService, CarService carService) {
        this.rentRepository = rentRepository;
        this.personService = personService;
        this.discountService = discountService;
        this.insuranceService = insuranceService;
        this.carService = carService;
    }

    public Set<Rent> getAllCarsRents(int carId) {
        return rentRepository.findRentByCarsContainingCar(carId);
    }

    public Rent getRent(int rentId) {
        Optional<Rent> rent = rentRepository.findById(rentId);
        return rent.orElse(null);
    }


    @Transactional
    public int saveNewRent(Rent rent) throws Exception {
        for (Car car : rent.getCars()) {
            carService.changeCarStateTo(CarStatus.RENTED, car.getId());
        }
        Rent result = rentRepository.save(rent);
        return result.getId();
    }

}
