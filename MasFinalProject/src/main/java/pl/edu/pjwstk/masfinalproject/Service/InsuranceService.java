package pl.edu.pjwstk.masfinalproject.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Insurance;
import pl.edu.pjwstk.masfinalproject.repository.InsuranceRepository;

import java.util.List;

@Service
public class InsuranceService {
    private InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.getAllInsurances();
    }

    public Insurance getInsuranceById(int id) {
        return insuranceRepository.findById(id).orElse(null);
    }

    public void save(Insurance insurance) {
        insuranceRepository.save(insurance);
    }
}


