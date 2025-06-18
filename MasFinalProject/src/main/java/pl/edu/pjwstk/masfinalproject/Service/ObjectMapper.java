package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.masfinalproject.DTO.*;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Car.EngineType;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Model.Rent;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ObjectMapper {
    public CarDTO mapCarToCarDTO(Car car) {
        if(car.getEngines().contains(EngineType.ElectricEngine)) {
            return new CarDTO(
                    car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getCarType(),
                    car.getRentPrice(),
                    car.getCarStatus(),
                    List.of("Electric engine"),
                    car.getMaximumRange() + " km",
                    null,
                    null
            );
        }else if(car.getEngines().contains(EngineType.OilEngine)) {
           return new CarDTO(
                   car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getCarType(),
                    car.getRentPrice(),
                    car.getCarStatus(),
                    List.of("Oil engine"),
                    null,
                    car.getBenzineType(),
                    car.getManufacturerName()
           );
        }

        return new CarDTO(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getCarType(),
                car.getRentPrice(),
                car.getCarStatus(),
                List.of("Electric engine", "Oil engine"),
                car.getMaximumRange() + " km",
                car.getBenzineType(),
                car.getManufacturerName()
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

        DiscountDTO discountDTO = null;
        if(rent.getDiscount() != null) {
            discountDTO = mapDiscountToDiscountDTO(rent.getDiscount());
        }

        InsuranceDTO insuranceDTO = null;
        if(rent.getInsurance() != null) {
            insuranceDTO = mapInsuranceToInsuranceDTO(rent.getInsurance());
        }

        return new RentDTO(
                rent.getId(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getPrice(),
                rent.getFinalPrice(),
                result,
                discountDTO,
                insuranceDTO,
                mapPersonToPersonDTO(rent.getPerson())
        );
    }

    public List<RentDTO> mapAllRentsToRentDTO(Set<Rent> rents) {
        return rents.stream()
                .map(this::mapRentToRentDTO)
                .toList();
    }


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
