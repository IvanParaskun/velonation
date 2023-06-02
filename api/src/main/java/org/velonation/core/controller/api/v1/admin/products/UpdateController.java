package org.velonation.core.controller.api.v1.admin.products;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.datasource.entity.product.Category;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.datasource.entity.product.Scooter;
import org.velonation.core.exception.ConstraintViolationException;
import org.velonation.core.service.CategoryService;
import org.velonation.core.service.ProductService;
import org.velonation.core.controller.api.v1.ResponseBody;


@RestController
@RequestMapping("/api/v1/admin/products/update")
@RequiredArgsConstructor
public class UpdateController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @InitBinder
    void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PutMapping(path = "/bike", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> update(@ModelAttribute Bike bike) {
        return updateProduct(bike);
    }

    @PutMapping(path = "/scooter", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> update(@ModelAttribute Scooter scooter) {
        return updateProduct(scooter);
    }

    @PutMapping(path = "/accessory", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> update(@ModelAttribute Product accessory) {
        return updateProduct(accessory);
    }

    @PutMapping(path = "/category", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> update(@ModelAttribute Category category) {
        return updateCategory(category);
    }

    private ResponseEntity<ResponseBody> updateProduct(Product product) {
        try {
            productService.update(product);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseBody.builder()
                            .statusCode(HttpStatus.CREATED).build()
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

    private ResponseEntity<ResponseBody> updateCategory(Category category) {
        try {
            categoryService.update(category);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseBody.builder()
                            .statusCode(HttpStatus.CREATED).build()
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
}
