package org.velonation.core.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.velonation.core.datasource.entity.product.Scooter;
import java.util.List;


@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Integer> {
    List<Scooter> findScootersByAvailableIsTrue();
    Scooter findScooterById(Integer id);
}
