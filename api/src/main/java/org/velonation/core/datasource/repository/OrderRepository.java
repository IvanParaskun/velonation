package org.velonation.core.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.velonation.core.datasource.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	Order findOrderById(Integer id);
}
