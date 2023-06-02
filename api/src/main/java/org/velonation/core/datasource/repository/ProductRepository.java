package org.velonation.core.datasource.repository;

import org.velonation.core.datasource.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);
    Product findProductByAvailableAndId(Boolean available, Integer id);
    Product findProductByName(String name);
}
