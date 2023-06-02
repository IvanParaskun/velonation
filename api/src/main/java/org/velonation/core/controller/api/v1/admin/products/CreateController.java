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


@RestController("productCreateController")
@RequestMapping("/api/v1/admin/products/create")
@RequiredArgsConstructor
public class CreateController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @InitBinder
    void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping(path="/bike", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> create(@ModelAttribute Bike bike) {
        return createProduct(bike);
    }

    @PostMapping(path="/scooter", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> create(@ModelAttribute Scooter scooter) {
        return createProduct(scooter);
    }

    @PostMapping(path="/accessory", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> create(@ModelAttribute Product accessory) {
        return createProduct(accessory);
    }

    @PostMapping(path="/category", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> create(@ModelAttribute Category category) {
        return createCategory(category);
    }

    private ResponseEntity<ResponseBody> createProduct(Product product) {
        try {
            productService.create(product);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseBody.builder()
                            .statusCode(HttpStatus.CREATED)
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

    private ResponseEntity<ResponseBody> createCategory(Category category) {
        try {
            categoryService.create(category);
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
                    .body(org.velonation.core.controller.api.v1.ResponseBody.builder()
                            .statusCode(HttpStatus.BAD_REQUEST)
                            .violations(e.getViolations().stream().toList())
                            .build()
                    );
        }
    }
}
