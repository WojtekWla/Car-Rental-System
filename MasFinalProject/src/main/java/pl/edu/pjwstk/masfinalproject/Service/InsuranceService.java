package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.repository.InsuranceRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class InsuranceService {
    private InsuranceRepository insuranceRepository;
    private final Validator validator;

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.getAllInsurances();
    }

    public Insurance getInsuranceById(int id) {
        return insuranceRepository.findById(id).orElse(null);
    }

    public List<String> addNewInsurance(Insurance insurance) {
        Set<ConstraintViolation<Insurance>> violations = validator.validate(insurance);
        if(!violations.isEmpty()) {
            return violations.stream().map(ConstraintViolation::getMessage).toList();
        }

        insuranceRepository.save(insurance);
        return null;
    }
}


