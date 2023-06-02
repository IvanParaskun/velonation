package org.velonation.core.controller.api.v1.admin.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.service.OrderService;


@RestController("orderDeleteController")
@RequestMapping("/api/v1/admin/orders/delete")
@RequiredArgsConstructor
public class DeleteController {
    private final OrderService orderService;

    @DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> delete(@RequestBody Request request) {
        boolean res = orderService.delete(request.id);
        return ResponseEntity.status(res ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(res ? "Успешно" : "Заказ не найден");
    }

    private static class Request {
        public Integer id;
    }
}

