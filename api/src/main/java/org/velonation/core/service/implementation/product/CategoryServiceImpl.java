package org.velonation.core.service.implementation.product;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.datasource.entity.product.Category;
import org.velonation.core.datasource.repository.CategoryRepository;
import org.velonation.core.service.CategoryService;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import org.velonation.core.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final Validator validator;

    @PostConstruct
    public void postConstruct() {
        this.productService.setCategoryService(this);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAvailable() {
        return new ArrayList<>(findAll().stream().filter(Category::isAvailable).toList());
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category findAvailableById(Integer id) {
        Category category = findById(id);
        return category.isAvailable() ? category : null;
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public Category create(Category category) throws org.velonation.core.exception.ConstraintViolationException {
        category.setId(null);
        category.setBikes(null);

        checkViolations(category);

        if (findByName(category.getName()) != null)
            throw new org.velonation.core.exception.ConstraintViolationException(Set.of(
                    new org.velonation.core.exception.ConstraintViolation(
                            "name",
                            "Категория с таким названием уже существует"
                    )
            ));

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) throws org.velonation.core.exception.ConstraintViolationException {
        Category lastCategory = category.getId() == null ? null : findById(category.getId());

        if (lastCategory == null)
            throw new org.velonation.core.exception.ConstraintViolationException(Set.of(
                    new org.velonation.core.exception.ConstraintViolation(
                            "id",
                            "Категория не найдена"
                    )
            ));

        checkViolations(category);

        Category categorySameName = findByName(category.getName());
        if (categorySameName != null && !categorySameName.getId().equals(category.getId()))
            throw new org.velonation.core.exception.ConstraintViolationException(Set.of(
                    new org.velonation.core.exception.ConstraintViolation(
                            "name",
                            "Категория с таким названием уже существует"
                    )
            ));

        category.setBikes(lastCategory.getBikes());
        return categoryRepository.save(category);
    }

    private void checkViolations(Category category) throws org.velonation.core.exception.ConstraintViolationException {
        Set<jakarta.validation.ConstraintViolation<Category>> violations = validator.validate(category);
        if (violations.size() != 0)
            throw new org.velonation.core.exception.ConstraintViolationException(violations
                    .stream()
                    .map(org.velonation.core.exception.ConstraintViolation::fromJakartaConstraintViolation).collect(Collectors.toSet()));
    }

    @Override
    public boolean delete(Integer id) {
        Category category = findById(id);

        if (category == null)
            return false;

        for (Bike bike: category.getBikes())
            productService.detachCategoryFromBike(bike.getId());

        categoryRepository.delete(category);
        return true;
    }
}
