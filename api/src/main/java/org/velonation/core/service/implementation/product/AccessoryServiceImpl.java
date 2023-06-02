package org.velonation.core.service.implementation.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.datasource.entity.product.Scooter;
import org.velonation.core.datasource.repository.ProductRepository;
import org.velonation.core.service.AccessoryService;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().filter(product -> !(product instanceof Bike) && !(product instanceof Scooter)).toList();
    }

    @Override
    public List<Product> findAvailable() {
        return findAll().stream().filter(Product::isAvailable).toList();
    }

    @Override
    public Product findById(Integer id) {
        Product product = productRepository.findProductById(id);

        if (!(product instanceof Bike) && !(product instanceof Scooter))
            return product;

        return null;
    }

    @Override
    public Product findAvailableById(Integer id) {
        Product product = findById(id);
        return product.isAvailable() ? product : null;
    }
}
