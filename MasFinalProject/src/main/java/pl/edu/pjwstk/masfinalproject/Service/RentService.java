package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.DTO.RentDTO;
import pl.edu.pjwstk.masfinalproject.Model.Rent;
import pl.edu.pjwstk.masfinalproject.repository.RentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RentService {
    private RentRepository rentRepository;
    private ObjectMapper objectMapper;
    public RentService(RentRepository rentRepository, ObjectMapper objectMapper) {
        this.rentRepository = rentRepository;
        this.objectMapper = objectMapper;
    }

    public Set<Rent> getAllCarsRents(int carId) {
        return  rentRepository.findRentByCarsContainingCar(carId);
//        List<RentDTO> rentDTOs = new ArrayList<>();
//
//        for (Rent rent : rents) {
//            rentDTOs.add(objectMapper.mapRentToRentDTO(rent));
//        }
//
//        return rentDTOs;
    }

    public Rent getRent(int rentId) {
        Optional<Rent> rent = rentRepository.findById(rentId);

        return rent.orElse(null);
    }

    public void saveNewRent(RentDTO rent) {

    }

}
