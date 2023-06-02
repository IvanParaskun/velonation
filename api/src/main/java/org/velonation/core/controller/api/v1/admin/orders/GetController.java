package org.velonation.core.controller.api.v1.admin.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.velonation.core.datasource.entity.order.Order;
import org.velonation.core.service.OrderService;
import java.util.List;


@RestController("ordersGetController")
@RequestMapping("/api/v1/admin/orders")
@RequiredArgsConstructor
public class GetController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> get() {
        return orderService.findAll();
    }
}