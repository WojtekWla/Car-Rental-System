package pl.edu.pjwstk.masfinalproject.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.masfinalproject.Model.*;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Car.EngineType;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarType;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Admin;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Consultant;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.Employee;
import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.FullTime;
import pl.edu.pjwstk.masfinalproject.Model.Person.Employee.TypeOfContract.PartTime;
import pl.edu.pjwstk.masfinalproject.repository.*;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


@Component
@AllArgsConstructor
public class InsertToDatabase {
    private EmployeeRepository employeeRepository;
    private PersonRepository personRepository;
    private AdminRepository adminRepository;
    private ConsultantRepository consultantRepository;
    private FullTimeRepository fullTimeRepository;
    private PartTimeRepository partTimeRepository;
    private CustomerRepository customerRepository;
    private DiscountRepository discountRepository;
    private InsuranceRepository insuranceRepository;
    private CarRepository carRepository;
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

        FullTime adminContract = FullTime.builder()
                .salary(4800)
                .build();

        Admin admin = Admin.builder()
                .name("David")
                .surname("Brown")
                .telephone("456789123")
                .pesel("44051401359")
                .email("david.brown@example.com")
                .birthDate(LocalDate.of(1982, 12, 30))
                .gender("Male")
                .residentialAddress("321 Pine St, Leeds")
                .hireDate(LocalDate.of(2018, 9, 5))
                .assignedRegion("Northern Region")
                .typeOfContract(adminContract)
                .build();

        PartTime consultantContract1 = PartTime.builder()
                .hoursPerWeek(25)
                .hourlySalary(30)
                .terminationDate(LocalDate.now().plusMonths(2))
                .build();

        Consultant consultant1 = Consultant.builder()
                .name("Alice")
                .surname("Smith")
                .pesel("02070803628")
                .telephone("123456789")
                .email("alice.smith@example.com")
                .birthDate(LocalDate.of(1990, 5, 10))
                .gender("Female")
                .residentialAddress("123 Main St, London")
                .hireDate(LocalDate.of(2020, 3, 1))
                .languages(List.of("English", "German"))
                .position("Senior Consultant")
                .typeOfContract(consultantContract1)
                .build();

        FullTime consultantContract2 = FullTime.builder()
                .salary(5200)
                .bonus(750)
                .build();

        Consultant consultant2 = Consultant.builder()
                .name("Bob")
                .surname("Johnson")
                .pesel("99123112346")
                .telephone("987654321")
                .email("bob.johnson@example.com")
                .birthDate(LocalDate.of(1985, 8, 15))
                .gender("Male")
                .residentialAddress("456 Elm St, Manchester")
                .hireDate(LocalDate.of(2019, 7, 20))
                .languages(List.of("English", "Spanish"))
                .position("Consultant")
                .typeOfContract(consultantContract2)
                .build();

        PartTime consultantContract3 = PartTime.builder()
                .hoursPerWeek(30)
                .hourlySalary(20)
                .terminationDate(LocalDate.now().plusWeeks(8))
                .build();

        Consultant consultant3 = Consultant.builder()
                .name("Claire")
                .surname("Williams")
                .pesel("05261278903")
                .telephone("321654987")
                .email("claire.williams@example.com")
                .birthDate(LocalDate.of(1993, 1, 5))
                .gender("Female")
                .residentialAddress("789 Oak St, Bristol")
                .hireDate(LocalDate.of(2021, 2, 10))
                .languages(List.of("English", "French"))
                .position("Junior Consultant")
                .typeOfContract(consultantContract3)
                .build();

        adminContract.setEmployee(admin);
        adminRepository.save(admin);

        consultantContract1.setEmployee(consultant1);
        consultantContract2.setEmployee(consultant2);
        consultantContract3.setEmployee(consultant3);

        consultantRepository.save(consultant1);
        consultantRepository.save(consultant2);
        consultantRepository.save(consultant3);

        Customer customer1 = Customer.builder()
                .name("Alice")
                .surname("Johnson")
                .telephone("123456789")
                .pesel("06120317480")
                .email("alice.johnson@example.com")
                .birthDate(LocalDate.of(1995, 3, 15))
                .gender("Female")
                .residentialAddress("123 Green St")
                .billingAddress("123 Green St")
                .points(120)
                .build();

        Customer customer2 = Customer.builder()
                .name("Bob")
                .surname("Miller")
                .pesel("83040514621")
                .telephone("987654321")
                .email("bob.miller@example.com")
                .birthDate(LocalDate.of(1988, 11, 23))
                .gender("Male")
                .residentialAddress("123 Green St")
                .points(300)
                .build();

        Discount seniorDiscount = Discount.builder()
                .typeOfDiscount("Senior Discount")
                .description("Discount for customers above 65 years old")
                .percentage(10)
                .build();

        Discount goldenMemberDiscount = Discount.builder()
                .typeOfDiscount("Golden Member Discount")
                .description("Discount for customers with more than 50 points")
                .percentage(20)
                .build();

        Discount platinumMemberDiscount = Discount.builder()
                .typeOfDiscount("Platinum Member Discount")
                .description("Discount for customers with more than 100 points")
                .percentage(30)
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

        Car car1 = Car.builder()
                .brand("Toyota")
                .model("Corolla")
                .rentPrice(200)
                .carStatus(CarStatus.AVAILABLE)
                .carType(CarType.COMBI)
                .travelledDistanceToService(5000)
                .engines(EnumSet.of(EngineType.OilEngine))
                .benzineType("95")
                .manufacturerName("Toyota Motors")
                .build();

        Car car2 = Car.builder()
                .brand("BMW")
                .model("X5")
                .rentPrice(300)
                .carStatus(CarStatus.AVAILABLE)
                .carType(CarType.SUV)
                .travelledDistanceToService(700)
                .engines(EnumSet.of(EngineType.OilEngine))
                .benzineType("98")
                .manufacturerName("BMW Group")
                .build();

        Car car3 = Car.builder()
                .brand("Tesla")
                .model("Model S")
                .rentPrice(400)
                .carStatus(CarStatus.AVAILABLE)
                .carType(CarType.SPORT)
                .travelledDistanceToService(100)
                .engines(EnumSet.of(EngineType.ElectricEngine))
                .maximumRange(500)
                .build();

        Car car4 = Car.builder()
                .brand("Toyota")
                .model("Prius Hybrid")
                .rentPrice(250)
                .carStatus(CarStatus.AVAILABLE)
                .carType(CarType.SPORT)
                .travelledDistanceToService(300)
                .engines(EnumSet.of(EngineType.OilEngine, EngineType.ElectricEngine))
                .benzineType("95")
                .manufacturerName("Toyota Motors")
                .maximumRange(300)
                .build();

        Mechanic mechanic1 = Mechanic.builder()
                .name("John Mechanic")
                .telephone("123456789")
                .email("john.mechanic@example.com")
                .location("Warsaw")
                .providedServices(List.of("Engine repair", "Oil change", "Brake service"))
                .build();

        Mechanic mechanic2 = Mechanic.builder()
                .name("Eva Fixit")
                .telephone("987654321")
                .email("eva.mechanic@example.com")
                .location("Gdansk")
                .providedServices(List.of("Transmission diagnostics", "AC service"))
                .build();


        Rent rent = Rent.builder()
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(5))
                .person(customer1)
                .discount(platinumMemberDiscount)
                .insurance(basicInsurance)
                .cars(Set.of(car1, car2))
                .build();

        Rent rent2 = Rent.builder()
                .startDate(LocalDate.now().plusDays(10))
                .endDate(LocalDate.now().plusDays(15))
                .person(customer1)
                .discount(goldenMemberDiscount)
                .insurance(premiumInsurance)
                .cars(Set.of(car3, car1))
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        discountRepository.save(seniorDiscount);
        discountRepository.save(goldenMemberDiscount);
        discountRepository.save(platinumMemberDiscount);
        insuranceRepository.save(basicInsurance);
        insuranceRepository.save(premiumInsurance);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        mechanicRepository.save(mechanic1);
        mechanicRepository.save(mechanic2);

        rentRepository.save(rent);
        rentRepository.save(rent2);

        car1.setRents(Set.of(rent, rent2));
        car2.setRents(Set.of(rent));
        car3.setRents(Set.of(rent2));
        customer1.setRents(Set.of(rent, rent2));

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        customerRepository.save(customer1);

        Review review1 = Review.builder()
                .message("Great ride, smooth and efficient!")
                .publishDate(LocalDate.now())
                .customer(customer1)
                .car(car1)
                .build();

        Review review2 = Review.builder()
                .message("Battery range could be better, but overall a nice experience.")
                .publishDate(LocalDate.now())
                .customer(customer1)
                .car(car2)
                .build();

        Service service1 = Service.builder()
                .dateOfService(LocalDate.of(2025, 5, 1))
                .price(150)
                .typeOfService("Oil Change")
                .car(car1)
                .mechanic(mechanic1)
                .build();

        Service service3 = Service.builder()
                .dateOfService(LocalDate.of(2025, 5, 11))
                .price(150)
                .typeOfService("Engine fix")
                .car(car1)
                .mechanic(mechanic1)
                .build();

        Service service2 = Service.builder()
                .dateOfService(LocalDate.of(2025, 5, 10))
                .price(300)
                .typeOfService("Brake Service")
                .car(car2)
                .mechanic(mechanic2)
                .build();

        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);
        reviewRepository.save(review1);
        reviewRepository.save(review2);
    }
}
