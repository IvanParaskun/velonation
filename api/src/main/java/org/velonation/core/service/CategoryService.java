package org.velonation.core.service;

import org.velonation.core.datasource.entity.product.Category;
import org.velonation.core.exception.ConstraintViolationException;
import java.util.List;


public interface CategoryService {
    List<Category> findAll();
    List<Category> findAvailable();
    Category findById(Integer id);
    Category findAvailableById(Integer id);
    Category findByName(String name);

    Category create(Category category) throws ConstraintViolationException;
    Category update(Category category) throws ConstraintViolationException;

    boolean delete(Integer id);
}
