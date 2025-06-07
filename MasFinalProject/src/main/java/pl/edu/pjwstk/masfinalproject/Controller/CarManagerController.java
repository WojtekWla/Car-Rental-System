package pl.edu.pjwstk.masfinalproject.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.pjwstk.masfinalproject.DTO.CarDTO;
import pl.edu.pjwstk.masfinalproject.DTO.InsuranceDTO;
import pl.edu.pjwstk.masfinalproject.DTO.PersonDTO;
import pl.edu.pjwstk.masfinalproject.DTO.RentDTO;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Service.*;
import pl.edu.pjwstk.masfinalproject.Service.Validator.Validator;
import pl.edu.pjwstk.masfinalproject.Session.RentAdd;

import java.time.LocalDate;
import java.util.*;

@Controller()
public class CarManagerController {

    private CarService carService;
    private RentService rentService;
    private PersonService personService;
    private InsuranceService insuranceService;
    private DiscountService discountService;

    private RentAdd rentAdd;
    private ObjectMapper objectMapper;

    public CarManagerController(CarService carService, RentService rentService, PersonService personService, InsuranceService insuranceService, DiscountService discountService, RentAdd rentAdd, ObjectMapper objectMapper) {
        this.carService = carService;
        this.rentService = rentService;
        this.personService = personService;
        this.insuranceService = insuranceService;
        this.discountService = discountService;
        this.rentAdd = rentAdd;
        this.objectMapper = objectMapper;
    }


    @GetMapping("/")
    public String mainPage(Model model) {
        List<CarDTO> cars = carService.getAllCars();
        model.addAttribute("cars", cars);

        return "mainPage";
    }

    @GetMapping("/car/{id}")
    public String carPage(Model model, @PathVariable int id) {
        CarDTO car = carService.getCarById(id);
        List<RentDTO> rentDTOS = objectMapper.mapAllRentsToRentDTO(rentService.getAllCarsRents(car.getId()));

        model.addAttribute("car", car);
        model.addAttribute("rents", rentDTOS);

        return "carPage";
    }

    @GetMapping("/rent/{id}")
    public String rentPage(Model model, @PathVariable int id) {
        var rent = rentService.getRent(id);
        if (rent != null) {
            var rentDTO = objectMapper.mapRentToRentDTO(rent);
            model.addAttribute("rent", rentDTO);
            return "rentPage";
        }

        return "redirect:/errorPage";
    }

    @GetMapping("/checkCarAvailability")
    public String checkCarAvailability(RedirectAttributes redirectAttributes) {
        if(carService.checkCarAvailability()) {
            return "redirect:/searchCustomer";
        }else {
            redirectAttributes.addFlashAttribute("message", "Currently there are no available cars to rent");
            return "redirect:/errorPage";
        }
    }

    @GetMapping("/searchCustomer")
    public String searchCustomer(Model model) {
        rentAdd.newRent();
        model.addAttribute("person", new PersonDTO());
        return "searchCustomer";
    }

    @PostMapping("/findPerson")
    public String findPerson(PersonDTO person, RedirectAttributes redirectAttributes) {
        var p = personService.findPerson(person);
        if(p.isPresent()) {
            rentAdd.newRent();
            rentAdd.getRent().setPerson(objectMapper.mapPersonToPersonDTO(p.get()));
            return "redirect:/displayCars";
        }else {
            redirectAttributes.addFlashAttribute("message", "Customer doesn't exist<br>Input correct data");
            return "redirect:/searchCustomer";
        }
    }

    @GetMapping("/displayCars")
    public String displayCars(Model model) {
        System.out.println(rentAdd.getRent());
        List<CarDTO> cars = carService.getAvailableCars();
        model.addAttribute("cars", cars);
        return "displayCars";
    }

    @PostMapping("/processCars")
    public String processCars(RedirectAttributes redirectAttributes,
                              @RequestParam(required = false, name = "selectedCarIds") List<Integer> cars,
                              @RequestParam(required = false) String endDate) {
        if(cars == null || cars.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "At least one car has to be chosen");
            redirectAttributes.addFlashAttribute("uri", "/displayCars");
            redirectAttributes.addFlashAttribute("buttonText", "Go back to cars");
            return "redirect:/errorPage";
        }

        LocalDate ed = LocalDate.parse(endDate);

        if (ed.isBefore(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("message", "End date cannot be in the past");
            redirectAttributes.addFlashAttribute("uri", "/displayCars");
            redirectAttributes.addFlashAttribute("buttonText", "Go back to cars");
            return "redirect:/errorPage";
        }

        List<Integer> notAvailableCars = carService.checkCarsAvailability(cars);
        System.out.println("Not available cars" + notAvailableCars);
        if(!notAvailableCars.isEmpty()) {
            List<CarDTO> carsDTO = new ArrayList<>();
            for (Integer carId : notAvailableCars) {
                carsDTO.add(carService.getCarById(carId));
            }
            System.out.println("Not available cars" + carsDTO);
            redirectAttributes.addFlashAttribute("notAvailableCars", carsDTO);
            return "redirect:/errorCarsNotAvailable";
        }else {
            Set<CarDTO> carsDTO = new HashSet<>();
            for (Integer carId : cars) {
                carService.changeCarStateTo(CarStatus.RESERVED, carId);
                carsDTO.add(carService.getCarById(carId));
            }
            rentAdd.getRent().setRentedCars(carsDTO);
            rentAdd.getRent().setRentDate(LocalDate.now());
            rentAdd.getRent().setReturnDate(ed);

            return "redirect:/displayInsurances";
        }
    }

    @GetMapping("/errorCarsNotAvailable")
    public String carsNotAvailable(Model model) {
        return "errorCarsNotAvailable";
    }

    @GetMapping("/displayInsurances")
    public String displayInsurances(Model model) {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        model.addAttribute("insurances", objectMapper.mapAllToInsuranceDTO(insurances));
        return "displayInsurances";
    }

    @PostMapping("/processInsurance")
    public String processInsurance(@RequestParam(required = false, name = "selectedInsuranceId") String insuranceId) {

        if(insuranceId != null && !insuranceId.isEmpty()) {
            var insurance = insuranceService.getInsuranceById(Integer.parseInt(insuranceId));

            if(insurance != null) {
                rentAdd.getRent().setInsurance(objectMapper.mapInsuranceToInsuranceDTO(insurance));
            }else {
                throw new IllegalArgumentException("Insurance doesn't exist");
            }
        }
        return "redirect:/displayDiscounts";
    }

    @GetMapping("/displayDiscounts")
    public String displayDiscounts(Model model) {
        List<Discount> discounts = discountService.getDiscounts();
        model.addAttribute("discounts", objectMapper.mapAllToDiscountDTO(discounts));
        return "displayDiscounts";
    }


    @PostMapping("/processDiscount")
    public String processDiscount(
            RedirectAttributes redirectAttributes,
            @RequestParam(required = false, name = "selectedDiscountId") String discountId
    ) {
        if(discountId != null && !discountId.isEmpty()) {
            var validated = discountService.validatePerson(rentAdd.getRent().getPerson(), Integer.parseInt(discountId));
            if(!validated) {
                redirectAttributes.addFlashAttribute("message", "Person is not eligible for this discount ");
                redirectAttributes.addFlashAttribute("uri", "/displayDiscounts");
                redirectAttributes.addFlashAttribute("buttonText", "Go back to discounts");

                return "redirect:/errorPage";
            }

            var discount = discountService.getDiscountById(Integer.parseInt(discountId));
            discount.ifPresentOrElse(
                    value -> rentAdd.getRent().setDiscount(objectMapper.mapDiscountToDiscountDTO(value)),
                    () -> { throw new IllegalArgumentException("Discount doesn't exist"); }
            );
        }

        return "redirect:/saveRent";
    }

    @GetMapping("/saveRent")
    public String saveRent(Model model) {
        if(rentAdd.getRent() == null) {
            return "redirect:/errorPage";
        }
        rentService.saveNewRent(rentAdd.getRent());

        return "redirect:/rent/";
    }

    @GetMapping("/errorPage")
    public String error(Model model) {
        if(!model.containsAttribute("message")) {
            model.addAttribute("message", "Internal server error");
        }
        return "error";
    }
}
