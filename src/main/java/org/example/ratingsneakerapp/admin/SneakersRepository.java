package org.example.ratingsneakerapp.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface SneakersRepository extends JpaRepository<Sneaker, Long> {
    Sneaker findById(int id);
    List<Sneaker> findTop5ByOrderByRatingDesc();
    List<Sneaker> findAll();
     List<Sneaker> findByBrand(String brand);
    List<Sneaker> findByModel(String model);
    List<Sneaker> findByBrandAndModel(String brand, String model);
}
