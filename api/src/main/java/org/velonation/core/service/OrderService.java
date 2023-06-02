package org.velonation.core.service;

import org.velonation.core.datasource.entity.order.Order;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.exception.ConstraintViolationException;

import java.util.List;
import java.util.Map;


public interface OrderService {
	List<Order> findAll();
	List<Order> findByProduct(Product product);

	Order save(Order order) throws ConstraintViolationException;
	boolean delete(Integer id);

	Map<Product, Integer> translateCart(Map<Integer, Integer> entry) throws ConstraintViolationException;
}
