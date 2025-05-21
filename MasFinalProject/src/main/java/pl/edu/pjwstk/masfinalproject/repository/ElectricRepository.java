package pl.edu.pjwstk.masfinalproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.masfinalproject.Model.Car.ElectricEngine;

public interface ElectricRepository extends CrudRepository<ElectricEngine, Integer> {
}
