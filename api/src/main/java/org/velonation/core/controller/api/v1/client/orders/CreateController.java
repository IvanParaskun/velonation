package org.velonation.core.controller.api.v1.client.orders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.datasource.entity.order.Order;
import org.velonation.core.exception.ConstraintViolationException;
import org.velonation.core.service.OrderService;
import org.velonation.core.controller.api.v1.ResponseBody;
import java.util.Map;


@RestController("orderCreateController")
@RequestMapping("/api/v1/orders/create")
@RequiredArgsConstructor
public class CreateController {
    private final OrderService orderService;

    @InitBinder
    void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseBody> submit(@RequestBody OrderForm form) {
        try {
            Order order = orderService.save(form.toOrder(orderService));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseBody.builder()
                            .statusCode(HttpStatus.CREATED)
                            .message(order.getId().toString())
                            .build()
                    );
        } catch (ConstraintViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseBody.builder()
                            .statusCode(HttpStatus.BAD_REQUEST)
                            .violations(e.getViolations().stream().toList())
                            .build()
                    );
        }
    }

    @Getter @Setter @ToString
    public static class OrderForm {
        private String email;
        private String phoneNumber;
        private Order.DeliveryInfo deliveryInfo;
        private Map<Integer, Integer> cart;

        public Order toOrder(OrderService orderService) throws ConstraintViolationException {
            Order order = new Order();
            order.setEmail(this.getEmail());
            order.setPhoneNumber(this.getPhoneNumber());
            order.setDeliveryInfo(deliveryInfo);
            order.setCart(orderService.translateCart(this.cart));
            return order;
        }
    }
}
