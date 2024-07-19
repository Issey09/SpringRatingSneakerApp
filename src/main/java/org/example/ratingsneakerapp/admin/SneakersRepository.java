package org.example.ratingsneakerapp.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SneakersRepository extends JpaRepository<Sneaker, Long> {
    Sneaker findByBrand(String brand);
}
