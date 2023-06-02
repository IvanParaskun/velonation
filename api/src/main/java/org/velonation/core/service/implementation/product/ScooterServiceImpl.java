package org.velonation.core.service.implementation.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.velonation.core.datasource.entity.product.Scooter;
import org.velonation.core.datasource.repository.ScooterRepository;
import org.velonation.core.service.ScooterService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ScooterServiceImpl implements ScooterService {
    private final ScooterRepository scooterRepository;

    @Override
    public List<Scooter> findAll() {
        return scooterRepository.findAll();
    }

    @Override
    public List<Scooter> findAvailable() {
        return scooterRepository.findScootersByAvailableIsTrue();
    }

    @Override
    public Scooter findById(Integer id) {
        return scooterRepository.findScooterById(id);
    }

    @Override
    public Scooter findAvailableById(Integer id) {
        Scooter scooter = findById(id);
        return scooter.isAvailable() ? scooter : null;
    }

    @Override
    public Set<Integer> modelYears() {
        return findAvailable().stream().map(Scooter::getModelYear).collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> heights() {
        return findAvailable().stream().map(Scooter::getHeight).collect(Collectors.toSet());
    }

    @Override
    public Set<Integer> maxWeights() {
        return findAvailable().stream().map(Scooter::getMaxWeight).collect(Collectors.toSet());
    }
}
