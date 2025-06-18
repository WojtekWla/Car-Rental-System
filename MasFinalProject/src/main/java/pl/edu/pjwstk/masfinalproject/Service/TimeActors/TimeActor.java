package pl.edu.pjwstk.masfinalproject.Service.TimeActors;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.masfinalproject.Model.Enum.CarStatus;
import pl.edu.pjwstk.masfinalproject.Service.CarService;

@Component
@AllArgsConstructor
public class TimeActor {
    private final CarService carService;

    @Async
    @Scheduled(cron = "0 0 8 * * ?")
    @Transactional
    public void checkBrokenCars() {
        System.out.println("Extracting broken cars to delete");
        var cars = carService.getCarsToService();
        if(cars.isEmpty()) {
            System.out.println("No cars which require service");
        }else {
            System.out.println("Found cars which require service");
            for (var car : cars) {
                System.out.println(car.getId() + " " + car.getBrand() + " " + car.getModel());
            }
        }
    }

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkForService() {
        System.out.println("Searching cars which require service");
        var cars = carService.getCarsWithDistanceToService();
        for (var car : cars) {
            System.out.println(car.getId() + " " + car.getBrand() + " " + car.getModel() + ", requires service " + car.getTravelledDistanceToService());
            carService.sendToService(CarStatus.SERVICE_REQUIRED, 0, car.getId());
        }
    }
}
