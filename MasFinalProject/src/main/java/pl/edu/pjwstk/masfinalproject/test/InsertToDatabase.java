package pl.edu.pjwstk.masfinalproject.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.masfinalproject.Model.*;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Car.ElectricEngine;
import pl.edu.pjwstk.masfinalproject.Model.Car.OilEngine;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarType;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee;
import pl.edu.pjwstk.masfinalproject.Model.Person.User;
import pl.edu.pjwstk.masfinalproject.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Component
@AllArgsConstructor
public class InsertToDatabase {
    private EmployeeRepository employeeRepository;
    private PersonRepository personRepository;
    private UserRepository userRepository;
    private DiscountRepository discountRepository;
    private InsuranceRepository insuranceRepository;
    private CarRepository carRepository;
    private ElectricRepository electricRepository;
    private OilEngineRepository oilEngineRepository;
    private MechanicRepository mechanicRepository;
    private ReviewRepository reviewRepository;
    private RentRepository rentRepository;
    private ServiceRepository serviceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void insertData() {
        Iterable<Employee> employees = employeeRepository.findAll();
        if (employees.iterator().hasNext()) {
            return;
        }

        Employee employee = Employee.builder()
                .name("John")
                .surname("Doe")
                .telephone("123456789")
                .email("john.doe@example.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .residentialAddress("123 Main St")
                .salary(75000)
                .build();


        Employee employee2 = Employee.builder()
                .name("Anna")
                .surname("Smith")
                .telephone("987654321")
                .email("anna.smith@example.com")
                .birthDate(LocalDate.of(1985, 5, 15))
                .gender("Female")
                .residentialAddress("456 Elm St")
                .salary(62000)
                .build();


        User user1 = User.builder()
                .name("Alice")
                .surname("Johnson")
                .telephone("123456789")
                .email("alice.johnson@example.com")
                .birthDate(LocalDate.of(1995, 3, 15))
                .gender("Female")
                .residentialAddress("123 Green St")
                .billingAddress("123 Green St")
                .points(120)
                .build();

        User user2 = User.builder()
                .name("Bob")
                .surname("Miller")
                .telephone("987654321")
                .email("bob.miller@example.com")
                .birthDate(LocalDate.of(1988, 11, 23))
                .gender("Male")
                .residentialAddress("123 Green St")
                .points(300)
                .build();

        Discount summerDiscount = Discount.builder()
                .typeOfDiscount("Seasonal")
                .description("Summer sale discount for all vehicle types")
                .percentage(20)
                .build();

        Discount loyaltyDiscount = Discount.builder()
                .typeOfDiscount("Loyalty")
                .description("Loyal customers receive a discount")
                .percentage(15)
                .build();


        Insurance basicInsurance = Insurance.builder()
                .name("Basic Coverage")
                .description("Covers third-party damages and basic theft protection.")
                .price(150)
                .build();

        Insurance premiumInsurance = Insurance.builder()
                .name("Premium Coverage")
                .description("Includes full coverage with roadside assistance and zero deductible.")
                .price(300)
                .build();

        ElectricEngine tesla = ElectricEngine.builder()
                .brand("Tesla")
                .model("Model S")
                .rentPrice(500)
                .carStatus(CarStatus.AVAILABLE)
                .carType(CarType.SPORT)
                .travelledDistance(15000)
                .maximumRange(600)
                .build();

        ElectricEngine nissanLeaf = ElectricEngine.builder()
                .brand("Nissan")
                .model("Leaf")
                .rentPrice(300)
                .carStatus(CarStatus.AVAILABLE)
                .carType(CarType.COMBI)
                .travelledDistance(22000)
                .maximumRange(270)
                .build();


        OilEngine bmwDiesel = OilEngine.builder()
                .brand("BMW")
                .model("320d")
                .rentPrice(400)
                .carStatus(CarStatus.AVAILABLE)
                .carType(CarType.SPORT)
                .travelledDistance(80000)
                .oilType("Diesel")
                .manufacturerName("BMW AG")
                .build();

        OilEngine audiPetrol = OilEngine.builder()
                .brand("Audi")
                .model("A4")
                .rentPrice(420)
                .carStatus(CarStatus.RENTED)
                .carType(CarType.SPORT)
                .travelledDistance(50000)
                .oilType("Petrol")
                .manufacturerName("Audi AG")
                .build();

        Mechanic mechanic1 = Mechanic.builder()
                .name("John Mechanic")
                .location("Warsaw")
                .providedServices(List.of("Engine repair", "Oil change", "Brake service"))
                .build();

        Mechanic mechanic2 = Mechanic.builder()
                .name("Eva Fixit")
                .location("Gdansk")
                .providedServices(List.of("Transmission diagnostics", "AC service"))
                .build();


        Rent rent = Rent.builder()
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(5))
                .person(user1)
                .discount(summerDiscount)
                .insurance(basicInsurance)
                .cars(Set.of(bmwDiesel, audiPetrol))
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        userRepository.save(user1);
        userRepository.save(user2);
        discountRepository.save(summerDiscount);
        discountRepository.save(loyaltyDiscount);
        insuranceRepository.save(basicInsurance);
        insuranceRepository.save(premiumInsurance);
        electricRepository.save(tesla);
        electricRepository.save(nissanLeaf);
        oilEngineRepository.save(audiPetrol);
        oilEngineRepository.save(bmwDiesel);
        mechanicRepository.save(mechanic1);
        mechanicRepository.save(mechanic2);

        rentRepository.save(rent);

        bmwDiesel.setRents(Set.of(rent));
        audiPetrol.setRents(Set.of(rent));
        user1.setRents(Set.of(rent));

        oilEngineRepository.save(bmwDiesel);
        oilEngineRepository.save(audiPetrol);
        userRepository.save(user1);

        Review review1 = Review.builder()
                .message("Great ride, smooth and efficient!")
                .publishDate(LocalDate.now())
                .person(user1)
                .car(audiPetrol)
                .build();

        Review review2 = Review.builder()
                .message("Battery range could be better, but overall a nice experience.")
                .publishDate(LocalDate.now())
                .person(user1)
                .car(bmwDiesel)
                .build();

        Service service1 = Service.builder()
                .dateOfService(LocalDate.of(2025, 5, 1))
                .price(150)
                .typeOfService("Oil Change")
                .car(bmwDiesel)
                .mechanic(mechanic1)
                .build();

        Service service3 = Service.builder()
                .dateOfService(LocalDate.of(2025, 5, 11))
                .price(150)
                .typeOfService("Engine fix")
                .car(bmwDiesel)
                .mechanic(mechanic1)
                .build();

        Service service2 = Service.builder()
                .dateOfService(LocalDate.of(2025, 5, 10))
                .price(300)
                .typeOfService("Brake Service")
                .car(tesla)
                .mechanic(mechanic2)
                .build();

        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);
        reviewRepository.save(review1);
        reviewRepository.save(review2);

    }

    public void getData() {
        getEmployees();
        getUsers();

        getRents();

        getReviews();
        getServices();
    }

    public void getEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        employees.forEach(System.out::println);
    }

    public void getUsers() {
        Iterable<User> users =  userRepository.findAll();
        users.forEach(System.out::println);
    }

    public void getRents() {
        System.out.println("Rents");
        Iterable<Rent> rents = rentRepository.findAll();
        rents.forEach(rent -> {
            System.out.println(rent);
            System.out.println("Person: " + rent.getPerson());
            System.out.println("Discount: " + rent.getDiscount());
            System.out.println("Insurance: " + rent.getInsurance());
            Iterable<Car> cars = carRepository.findByRentId(rent.getId());
            System.out.println("Cars: " + cars);
        });
    }

    public void getReviews() {
        System.out.println("Review");
        Iterable<Review> reviews = reviewRepository.findAll();
        reviews.forEach(review -> {
            System.out.println(review);
            System.out.println("Car: " + review.getCar());
            System.out.println("Person: " + review.getPerson());
        });
    }

    public void getServices()
    {
        System.out.println("Service, mechanic, car");
        Iterable<Service> services = serviceRepository.findAll();
        services.forEach(service -> {
            System.out.println(service);
            System.out.println("Car: " + service.getCar());
            System.out.println("Mechanic: " + service.getMechanic());
        });
    }
}
