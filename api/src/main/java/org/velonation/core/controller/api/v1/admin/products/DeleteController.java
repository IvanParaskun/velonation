package org.velonation.core.controller.api.v1.admin.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.exception.InUseException;
import org.velonation.core.service.CategoryService;
import org.velonation.core.service.ProductService;


@RestController("productDeleteController")
@RequestMapping("/api/v1/admin/products/delete")
@RequiredArgsConstructor
public class DeleteController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> delete(@RequestBody Request request) {
        try {
            boolean res = productService.delete(request.id);
            return ResponseEntity.status(res ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(res ? "Успешно" : "Товар не найден");
        } catch (InUseException e) {
            return ResponseEntity.status(HttpStatus.IM_USED).body("Товар содержится в заказе");
        }
    }

    @DeleteMapping("/category")
    public ResponseEntity<String> deleteCategory(@RequestBody Request request) {
        boolean res = categoryService.delete(request.id);
        return ResponseEntity.status(res ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(res ? "Успешно" : "Категория не найдена");
    }

    private static class Request {
        public Integer id;
    }
}
