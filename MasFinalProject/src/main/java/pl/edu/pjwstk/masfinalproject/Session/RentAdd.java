package pl.edu.pjwstk.masfinalproject.Session;


import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.pjwstk.masfinalproject.DTO.RentDTO;

@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RentAdd {
    private RentDTO rent;

    public void newRent() {
        this.rent = new RentDTO();
    }
    public RentDTO getRent() {
        return rent;
    }
}
