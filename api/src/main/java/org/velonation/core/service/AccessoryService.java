package org.velonation.core.service;

import org.velonation.core.datasource.entity.product.Product;
import java.util.List;

public interface AccessoryService {
    List<Product> findAll();
    List<Product> findAvailable();

    Product findById(Integer id);
    Product findAvailableById(Integer id);
}
