package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.masfinalproject.DTO.*;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Model.Rent;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ObjectMapper {
    public CarDTO mapCarToCarDTO(Car car) {
        return new CarDTO(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getCarType(),
                car.getRentPrice(),
                car.getCarStatus()
        );
    }

    public DiscountDTO mapDiscountToDiscountDTO(Discount discount) {
        return new DiscountDTO(
                discount.getId(),
                discount.getTypeOfDiscount(),
                discount.getDescription(),
                discount.getPercentage()
        );
    }

    public InsuranceDTO mapInsuranceToInsuranceDTO(Insurance insurance) {
        return new InsuranceDTO(
                insurance.getId(),
                insurance.getName(),
                insurance.getDescription(),
                insurance.getPrice()
        );
    }

    public RentDTO mapRentToRentDTO(Rent rent) {
        Set<Car> cars = rent.getCars();
        Set<CarDTO> carDTOs = new HashSet<>();

        for (Car car : cars) {
            carDTOs.add(mapCarToCarDTO(car));
        }
        Set<CarDTO> result = Collections.unmodifiableSet(carDTOs);

        return new RentDTO(
                rent.getId(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getPrice(),
                rent.getFinalPrice(),
                result,
                mapDiscountToDiscountDTO(rent.getDiscount()),
                mapInsuranceToInsuranceDTO(rent.getInsurance()),
                mapPersonToPersonDTO(rent.getPerson())
        );
    }

    public List<RentDTO> mapAllRentsToRentDTO(Set<Rent> rents) {
        return rents.stream()
                .map(this::mapRentToRentDTO)
                .toList();
    }

//    public Rent mapToRent(RentDTO rentDTO) {
//        return new Rent(
//                rentDTO.getRentDate(),
//                rentDTO.getReturnDate(),
//                rentDTO.getPerson(),
//                rentDTO.getDiscount(),
//                rentDTO.getRentedCars()
//        );
//    }

    public PersonDTO mapPersonToPersonDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getSurname(),
                person.getPesel()
        );
    }

    public InsuranceDTO mapToInsuranceDTO(Insurance insurance) {
        return new InsuranceDTO(
                insurance.getId(),
                insurance.getName(),
                insurance.getDescription(),
                insurance.getPrice()
        );
    }

    public List<InsuranceDTO> mapAllToInsuranceDTO(List<Insurance> insurances) {
        return insurances.stream()
                .map(this::mapToInsuranceDTO)
                .toList();
    }

    public DiscountDTO mapToDiscountDTO(Discount discount) {
        return new DiscountDTO(
                discount.getId(),
                discount.getTypeOfDiscount(),
                discount.getDescription(),
                discount.getPercentage()
        );
    }

    public List<DiscountDTO> mapAllToDiscountDTO(List<Discount> discounts) {
        return discounts.stream()
                .map(this::mapToDiscountDTO)
                .toList();
    }

}
