package org.velonation.core.controller.api.v1.admin.products;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.datasource.entity.product.Category;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.datasource.entity.product.Scooter;
import org.velonation.core.service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class GetController {
    private final BikeService bikeService;
    private final ScooterService scooterService;
    private final AccessoryService accessoryService;
    private final CategoryService categoryService;

    @GetMapping("/bikes")
    public List<Bike> bikes() {
        List<Bike> bikes = bikeService.findAll();
        Collections.sort(bikes);
        return bikes;
    }

    @GetMapping("/bikes/categories")
    public List<Category> categories() {
        List<Category> categories = categoryService.findAll();
        Collections.sort(categories);
        return categories;
    }

    @GetMapping("/scooters")
    public List<Scooter> scooters() {
        List<Scooter> scooters = scooterService.findAll();
        Collections.sort(scooters);
        return scooters;
    }

    @GetMapping("/accessories")
    public List<Product> accessories() {
        List<Product> accessories = new ArrayList<>(accessoryService.findAll());
        Collections.sort(accessories);
        return accessories;
    }

    @GetMapping("/bikes/{id}")
    public Bike bike(@PathVariable Integer id) { return bikeService.findById(id); }

    @GetMapping("/bikes/categories/{id}")
    public Category category(@PathVariable Integer id) { return categoryService.findById(id); }

    @GetMapping("/scooters/{id}")
    public Scooter scooter(@PathVariable Integer id) { return scooterService.findById(id); }

    @GetMapping("/accessories/{id}")
    public Product accessory(@PathVariable Integer id) { return accessoryService.findById(id); }
}
