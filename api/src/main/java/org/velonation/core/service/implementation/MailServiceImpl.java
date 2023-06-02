package org.velonation.core.service.implementation;

import lombok.RequiredArgsConstructor;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.springframework.stereotype.Service;
import org.velonation.core.datasource.entity.order.Order;
import org.velonation.core.service.MailService;
import services.Courier;
import services.SendService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
	@Override
	public void sendOrderNotification(Order order) {
		sendClientOrderNotification(order);
		sendAdminOrderNotification(order);
	}

	private void sendAdminOrderNotification(Order order) {
		HashMap<String, String> to = new HashMap<>();
		to.put("email", "velocity54@mail.ru");

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("orderID", order.getId().toString());
		data.put("email", order.getEmail());
		data.put("phone", order.getPhoneNumber());
		data.put("delivery", order.getDeliveryInfo() != null ? "Да" : "Нет");
		data.put("date", new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(new Date()));

		sendOrderNotification(to, "Q7A687DGTEM9Q7NDAVRSZQN0CSVH", data);
	}

	private void sendClientOrderNotification(Order order) {
		HashMap<String, String> to = new HashMap<>();
		to.put("email", order.getEmail());

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("orderID", order.getId().toString());
		data.put("total", order.total().toString() + "₽");
		data.put("delivery", order.getDeliveryInfo() != null ? "Да" : "Нет");

		sendOrderNotification(to, "GW1DVXXTVWM9XRN0PWR52570988D", data);
	}

	private void sendOrderNotification(HashMap<String, String> to, String template, HashMap<String, Object> data) {
		Courier.init("dk_prod_E68VP0WVF94FC2JX4J19YSX9GM4G");

		SendEnhancedRequestBody request = new SendEnhancedRequestBody();
		SendRequestMessage message = new SendRequestMessage();

		message.setTo(to);
		message.setTemplate(template);
		message.setData(data);

		request.setMessage(message);
		try {
			SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
