package org.velonation.core.controller.api.v1.client.products;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.datasource.entity.product.Category;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.datasource.entity.product.Scooter;
import org.velonation.core.service.AccessoryService;
import org.velonation.core.service.BikeService;
import org.velonation.core.service.CategoryService;
import org.velonation.core.service.ScooterService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController("clientProductsGetController")
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class GetController {
    private final BikeService bikeService;
    private final ScooterService scooterService;
    private final AccessoryService accessoryService;
    private final CategoryService categoryService;

    @GetMapping("/bikes")
    public List<Bike> bikes() {
        List<Bike> bikes = bikeService.findAvailable();
        Collections.sort(bikes);
        return bikes;
    }

    @GetMapping("/bikes/categories")
    public List<Category> categories() {
        List<Category> categories = categoryService.findAvailable();
        Collections.sort(categories);
        return categories;
    }

    @GetMapping("/scooters")
    public List<Scooter> scooters() {
        List<Scooter> scooters = scooterService.findAvailable();
        Collections.sort(scooters);
        return scooters;
    }

    @GetMapping("/accessories")
    public List<Product> accessories() {
        List<Product> accessories = new ArrayList<>(accessoryService.findAvailable());
        Collections.sort(accessories);
        return accessories;
    }

    @GetMapping("/bikes/{id}")
    public Bike bike(@PathVariable Integer id) {
        return bikeService.findAvailableById(id);
    }

    @GetMapping("/bikes/categories/{id}")
    public Category category(@PathVariable Integer id) {
        return categoryService.findAvailableById(id);
    }

    @GetMapping("/scooters/{id}")
    public Scooter scooter(@PathVariable Integer id) {
        return scooterService.findAvailableById(id);
    }

    @GetMapping("/accessories/{id}")
    public Product accessory(@PathVariable Integer id) {
        return accessoryService.findAvailableById(id);
    }
}
