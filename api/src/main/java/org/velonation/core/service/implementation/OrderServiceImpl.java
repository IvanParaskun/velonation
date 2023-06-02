package org.velonation.core.service.implementation;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.velonation.core.datasource.entity.order.Order;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.datasource.repository.OrderRepository;
import org.velonation.core.exception.ConstraintViolation;
import org.velonation.core.exception.ConstraintViolationException;
import org.velonation.core.service.MailService;
import org.velonation.core.service.OrderService;
import org.velonation.core.service.ProductService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final Validator validator;
	private final MailService mailService;
	private final ProductService productService;

	@PostConstruct
	public void postConstruct() {
		productService.setOrderService(this);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findByProduct(Product product) {
		return findAll().stream().filter(order -> order.getCart().containsKey(product)).toList();
	}

	@Override
	public Order save(Order order) throws ConstraintViolationException {
		order.setId(null);
		checkViolations(order);
		order = orderRepository.save(order);
		mailService.sendOrderNotification(order);
		return order;
	}

	private void checkViolations(Order order) throws org.velonation.core.exception.ConstraintViolationException {
		Set<jakarta.validation.ConstraintViolation<Order>> violations = validator.validate(order);
		if (violations.size() != 0)
			throw new org.velonation.core.exception.ConstraintViolationException(violations
					.stream()
					.map(org.velonation.core.exception.ConstraintViolation::fromJakartaConstraintViolation).collect(Collectors.toSet()));
	}

	@Override
	public boolean delete(Integer id) {
		Order order = orderRepository.findOrderById(id);

		if (order == null)
			return false;

		orderRepository.delete(orderRepository.findOrderById(id));
		return true;
	}

	@Override
	public Map<Product, Integer> translateCart(Map<Integer, Integer> entry) throws ConstraintViolationException {
		if (entry == null)
			return null;

		Map<Product, Integer> cart = new HashMap<>();

		for (Map.Entry<Integer, Integer> e: entry.entrySet()) {
			Product product = productService.findById(e.getKey());

			if (product == null)
				throw new ConstraintViolationException(Set.of(
						new ConstraintViolation(
								"product",
								"Корзина содержит несуществующие товары"
						)
				));

			if (!product.isAvailable())
				throw new ConstraintViolationException(Set.of(
						new ConstraintViolation(
								"product",
								"Корзина содержит недоступные товары"
						)
				));

			cart.put(product, e.getValue());
		}

		return cart;
	}
}
