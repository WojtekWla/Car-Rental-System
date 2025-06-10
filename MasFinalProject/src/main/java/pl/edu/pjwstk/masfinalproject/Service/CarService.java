package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.DTO.CarDTO;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;
    private ObjectMapper objectMapper;

    public CarService(CarRepository carRepository, ObjectMapper objectMapper) {
        this.carRepository = carRepository;
        this.objectMapper = objectMapper;
    }

    public List<CarDTO> getAllCars() {
        Iterable<Car> cars = carRepository.findAll();
        List<CarDTO> result = new ArrayList<>();
        for (Car car : cars) {
            result.add(objectMapper.mapCarToCarDTO(car));
        }

        return result;
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public boolean checkCarAvailability() {
        int count = carRepository.countCarsByCarStatus(CarStatus.AVAILABLE);
        return count > 0;
    }

    public List<CarDTO> getAvailableCars() {
        List<Car> cars = carRepository.getCarsByCarStatus(CarStatus.AVAILABLE);
        List<CarDTO> result = new ArrayList<>();
        for (Car car : cars) {
            result.add(objectMapper.mapCarToCarDTO(car));
        }

        return result;
    }

    public List<Integer> checkCarsAvailability(List<Integer> cars) {
        List<Integer> notAvailableCars = new ArrayList<>();
        for(Integer carId : cars) {
            int exists = carRepository.countCarsByCarStatusAndId(CarStatus.AVAILABLE, carId);
            if(exists == 0) {
                notAvailableCars.add(carId);
            }
        }

        return notAvailableCars;
    }

    public void changeCarStateTo(CarStatus carStatus, int carId) {
        carRepository.updateCarStatus(carStatus, carId);
    }

    public void save(Car car) {
        carRepository.save(car);
    }

}
