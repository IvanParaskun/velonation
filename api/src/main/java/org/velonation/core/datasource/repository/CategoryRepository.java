package org.velonation.core.datasource.repository;

import org.velonation.core.datasource.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);
    Category findCategoryByName(String name);
}
