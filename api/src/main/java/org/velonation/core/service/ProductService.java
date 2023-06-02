package org.velonation.core.service;

import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.exception.*;


public interface ProductService {
    void setOrderService(OrderService orderService);
    void setCategoryService(CategoryService categoryService);

    Product findById(Integer id);
    Product findByName(String name);

    Product create(Product product) throws ConstraintViolationException;
    Product update(Product product) throws ConstraintViolationException;
    boolean delete(Integer id) throws InUseException;

    void detachCategoryFromBike(Integer id);
}
