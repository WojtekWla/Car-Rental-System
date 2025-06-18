package pl.edu.pjwstk.masfinalproject.Controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.pjwstk.masfinalproject.DTO.CarDTO;
import pl.edu.pjwstk.masfinalproject.DTO.PersonDTO;
import pl.edu.pjwstk.masfinalproject.DTO.RentDTO;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Review;
import pl.edu.pjwstk.masfinalproject.Service.*;
import pl.edu.pjwstk.masfinalproject.Session.RentAdd;

import java.time.LocalDate;
import java.util.*;

@Controller()
@AllArgsConstructor
public class CarManagerController {

    private final CarService carService;
    private final RentService rentService;
    private final PersonService personService;
    private final InsuranceService insuranceService;
    private final DiscountService discountService;
    private final ReviewService reviewService;

    private final RentAdd rentAdd;
    private final ObjectMapper objectMapper;

    @GetMapping("/add")
    public String test(Model model) {
        reviewService.removeReview(3);

        return "redirect:/";
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        revertSessionChanges();
        List<CarDTO> cars = carService.getAllCars();
        model.addAttribute("cars", cars);

        return "mainPage";
    }

    @GetMapping("/car/{id}")
    public String carPage(Model model, @PathVariable int id) {
        Car car = carService.getCarById(id);
        if(car != null) {
            CarDTO carDTO = objectMapper.mapCarToCarDTO(car);
            List<RentDTO> rentDTOS = objectMapper.mapAllRentsToRentDTO(rentService.getAllCarsRents(car.getId()));

            model.addAttribute("car", carDTO);
            model.addAttribute("rents", rentDTOS);

            return "carPage";
        }
        return "redirect:/errorPage";
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
        revertSessionChanges();
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
            rentAdd.setPerson(p.get());
            return "redirect:/displayCars";
        }else {
            redirectAttributes.addFlashAttribute("message", "Customer doesn't exist<br>Input correct data");
            return "redirect:/searchCustomer";
        }
    }

    @GetMapping("/displayCars")
    public String displayCars(Model model) {

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
        if(!notAvailableCars.isEmpty()) {
            List<CarDTO> carsDTO = new ArrayList<>();
            for (Integer carId : notAvailableCars) {
                carsDTO.add(objectMapper.mapCarToCarDTO(carService.getCarById(carId)));
            }
            redirectAttributes.addFlashAttribute("notAvailableCars", carsDTO);
            return "redirect:/errorCarsNotAvailable";
        }else {
            Set<Car> carsDTO = new HashSet<>();
            for (Integer carId : cars) {
                carService.changeCarStateTo(CarStatus.RESERVED, carId);
                carsDTO.add(carService.getCarById(carId));
            }
            rentAdd.setRentData(carsDTO, LocalDate.now(), ed);
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
                rentAdd.setInsurance(insurance);
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
                    value -> rentAdd.setDiscount(value),
                    () -> { throw new IllegalArgumentException("Discount doesn't exist"); }
            );
        }

        return "redirect:/saveRent";
    }

    @GetMapping("/saveRent")
    public String saveRent(RedirectAttributes redirectAttributes) {
        if(rentAdd.getRent() == null) {
            return "redirect:/errorPage";
        }
        int id = -1;
        try {
            id = rentService.saveNewRent(rentAdd.getRent());
        } catch (Exception e) {
            rentAdd.removeRent();
            redirectAttributes.addFlashAttribute("message", "Unable to save new rent");
            redirectAttributes.addFlashAttribute("uri", "/");
            redirectAttributes.addFlashAttribute("buttonText", "Go back to main page");
            return "redirect:/errorPage";
        }

        rentAdd.removeRent();
        return "redirect:/rent/" + id;
    }

    @GetMapping("/errorPage")
    public String error(Model model) {
        if(!model.containsAttribute("message")) {
            model.addAttribute("message", "Internal server error");
        }
        return "error";
    }

    private void revertCarStateChanges() {
        if(rentAdd.getRent() != null && rentAdd.getRent().getCars() != null) {
            System.out.println("Reverting cars states");
            for (Car car : rentAdd.getRent().getCars()) {
                carService.changeCarStateTo(CarStatus.AVAILABLE, car.getId());
            }
            rentAdd.getRent().setCars(null);
        }
    }

    private void revertSessionChanges() {
        revertCarStateChanges();
        rentAdd.removeRent();
    }
}