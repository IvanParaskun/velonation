package org.velonation.core.service;

import org.velonation.core.datasource.entity.order.Order;

public interface MailService {
	void sendOrderNotification(Order order);
}
