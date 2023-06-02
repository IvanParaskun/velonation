package org.velonation.core.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.velonation.core.datasource.entity.product.Bike;
import java.util.List;


@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {
    List<Bike> findBikesByAvailableIsTrue();
    Bike findBikeById(Integer id);
}
