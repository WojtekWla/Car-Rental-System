package pl.edu.pjwstk.masfinalproject.Session;


import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Discount;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.Model.Person.Person;
import pl.edu.pjwstk.masfinalproject.Model.Rent;

import java.time.LocalDate;
import java.util.Set;

@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RentAdd {
    private Rent rent;

    public void newRent() {
        this.rent = new Rent();
    }
    public Rent getRent() {
        return rent;
    }

    public void removeRent() {
        this.rent = null;
    }

    public void setRentData(Set<Car> cars, LocalDate startDate, LocalDate endDate) {
        rent.setCars(cars);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
    }

    public void setInsurance(Insurance insurance) {
        rent.setInsurance(insurance);
    }

    public void setDiscount(Discount discount) {
        rent.setDiscount(discount);
    }


    public void setPerson(Person person) {
        rent.setPerson(person);
    }
}
