package org.velonation.core.service.implementation.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.datasource.repository.BikeRepository;
import org.velonation.core.service.BikeService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {
    private final BikeRepository bikeRepository;

    @Override
    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }

    @Override
    public List<Bike> findAvailable() {
        return bikeRepository.findBikesByAvailableIsTrue();
    }

    @Override
    public Bike findById(Integer id) {
        return bikeRepository.findBikeById(id);
    }

    @Override
    public Bike findAvailableById(Integer id) {
        Bike bike = findById(id);
        return bike.isAvailable() ? bike : null;
    }

    @Override
    public Bike findBannered() {
        return findAvailable().stream().filter(Bike::isBannered).findFirst().orElse(null);
    }

    @Override
    public List<Bike> findCarded() {
        return findAvailable().stream().filter(Bike::isCarded).toList();
    }

    @Override
    public Set<String> brands() {
        return findAvailable().stream().map(Bike::getBrand).collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> modelYears() {
        return findAvailable().stream().map(Bike::getModelYear).collect(Collectors.toSet());
    }
}
