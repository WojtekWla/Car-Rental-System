package pl.edu.pjwstk.masfinalproject.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.masfinalproject.Model.Car.Car;
import pl.edu.pjwstk.masfinalproject.Model.Person.Customer;
import pl.edu.pjwstk.masfinalproject.Model.Review;
import pl.edu.pjwstk.masfinalproject.repository.ReviewRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReviewService {
    private final Validator validator;
    private final ReviewRepository reviewRepository;

    public List<String> addReview(Review review, Car car, Customer customer) {
        Set<ConstraintViolation<Review>> violations = validator.validate(review);
        if (!violations.isEmpty()) {
            return violations.stream().map(ConstraintViolation::getMessage).toList();
        }

        review.setCar(car);
        review.setCustomer(customer);

        reviewRepository.save(review);
        return null;
    }


    public void removeReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
