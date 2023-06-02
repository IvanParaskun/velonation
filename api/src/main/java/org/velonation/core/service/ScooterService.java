package org.velonation.core.service;

import org.velonation.core.datasource.entity.product.Scooter;
import java.util.List;
import java.util.Set;


public interface ScooterService {
    List<Scooter> findAll();
    List<Scooter> findAvailable();

    Scooter findById(Integer id);
    Scooter findAvailableById(Integer id);

    Set<Integer> modelYears();
    Set<Integer> heights();
    Set<Integer> maxWeights();
}
