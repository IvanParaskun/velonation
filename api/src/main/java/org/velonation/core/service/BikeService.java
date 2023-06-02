package org.velonation.core.service;

import org.velonation.core.datasource.entity.product.Bike;
import java.util.List;
import java.util.Set;

public interface BikeService {
    List<Bike> findAll();
    List<Bike> findAvailable();

    Bike findById(Integer id);
    Bike findAvailableById(Integer id);

    Bike findBannered();
    List<Bike> findCarded();

    Set<String> brands();
    Set<Integer> modelYears();
}
