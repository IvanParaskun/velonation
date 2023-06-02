package org.velonation.core.service.implementation.product;

import lombok.RequiredArgsConstructor;
import org.velonation.core.datasource.entity.order.Order;
import org.velonation.core.datasource.entity.product.Bike;
import org.velonation.core.datasource.entity.product.Category;
import org.velonation.core.datasource.entity.product.Product;
import org.velonation.core.datasource.repository.ProductRepository;
import org.velonation.core.exception.*;
import org.velonation.core.service.*;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BikeService bikeService;
    private final ImageService imageService;
    private final Validator validator;
    private OrderService orderService;
    private CategoryService categoryService;

    @Override
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public Product create(Product product) throws ConstraintViolationException {
        product.setId(null);
        validate(product);

        Product productSameName = findByName(product.getName());
        if (productSameName != null && productSameName.getClass().equals(product.getClass()))
            throw new ConstraintViolationException(Set.of(
                    new ConstraintViolation(
                            "name",
                            "Товар с таким названием уже существует"
                    )
            ));

        product = attachCategory(product);

        if (product.getFile() == null || product.getFile().isEmpty())
            throw new ConstraintViolationException(Set.of(
                    new ConstraintViolation(
                            "file",
                            "Отсутствует изображение для нового товара")
            ));
        else
            saveImage(product);

        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) throws ConstraintViolationException {
        Product prevVersion = findById(product.getId());

        if (prevVersion == null)
            throw new ConstraintViolationException(Set.of(
                    new ConstraintViolation(
                            "global",
                            "Запрашиваемый товар не существует"
                    )
            ));

        product.setImageUrl(prevVersion.getImageUrl());
        validate(product);

        Product productSameName = findByName(product.getName());
        if (productSameName != null && productSameName.getClass().equals(product.getClass()) && !productSameName.getId().equals(product.getId()))
            throw new ConstraintViolationException(Set.of(
                    new ConstraintViolation(
                            "name",
                            "Товар с таким названием уже существует"
                    )
            ));

        if (orderService.findByProduct(prevVersion).size() != 0)
            throw new ConstraintViolationException(Set.of(
                    new ConstraintViolation(
                            "global",
                            "Товар содержится в заказе"
                    )
            ));

        product = attachCategory(product);

        if (product.getFile() != null && !product.getFile().isEmpty())
            product = saveImage(product);

        return productRepository.save(product);
    }

    @Override
    public boolean delete(Integer id) throws InUseException {
        Product product = findById(id);

        if (product == null)
            return false;

        List<Order> orders = orderService.findByProduct(product);

        if (orders != null && orders.size() > 0)
            throw new InUseException();

        productRepository.delete(product);
        return true;
    }

    @Override
    public void detachCategoryFromBike(Integer id) {
        Bike bike = bikeService.findById(id);

        if (bike != null) {
            bike.setCategory(null);
            productRepository.save(bike);
        }
    }

    private void validate(Product product) throws ConstraintViolationException {
        Set<jakarta.validation.ConstraintViolation<Product>> violations = validator.validate(product);
        if (violations.size() != 0)
            throw new ConstraintViolationException(violations
                    .stream()
                    .map(ConstraintViolation::fromJakartaConstraintViolation).collect(Collectors.toSet()));

        if (product instanceof Bike bike) {
            if (bike.isBannered()) {
                if (!bike.isAvailable())
                    throw new ConstraintViolationException(Set.of(
                            new ConstraintViolation("bannered", "Товар недоступен")
                    ));

                if (bike.getHeading() == null)
                    throw new ConstraintViolationException(Set.of(
                            new ConstraintViolation("bannered", "Товар должен содержать промо-текст")
                    ));

                Bike current = bikeService.findBannered();
                if (current != null && !current.equals(bike))
                    throw new ConstraintViolationException(Set.of(
                            new ConstraintViolation("bannered", "Баннер занят")
                    ));
            }

            if (bike.isCarded()) {
                if (!bike.isAvailable())
                    throw new ConstraintViolationException(Set.of(
                            new ConstraintViolation("carded", "Товар недоступен")
                    ));

                if (bike.getHeading() == null)
                    throw new ConstraintViolationException(Set.of(
                            new ConstraintViolation("carded", "Товар должен содержать промо-текст")
                    ));

                List<Bike> current = bikeService.findCarded();
                if (current != null && current.size() == 3 && !current.contains(bike))
                    throw new ConstraintViolationException(Set.of(
                            new ConstraintViolation("carded", "Все карточки заняты")
                    ));
            }
        }
    }

    private Product saveImage(Product product) throws ConstraintViolationException {
        try {
            return imageService.save(product);
        } catch (IOException e) {
            throw new ConstraintViolationException(Set.of(
                    new ConstraintViolation(
                            "file",
                            "Невозможно сохранить изображение"
                    )
            ));
        }
    }

    private Product attachCategory(Product product) throws ConstraintViolationException {
        if (product instanceof Bike bike) {
            if (bike.getCategoryId() == null)
                bike.setCategory(null);
            else {
                Category category = categoryService.findById(bike.getCategoryId());

                if (category == null)
                    throw new ConstraintViolationException(Set.of(
                            new ConstraintViolation(
                                    "categoryId",
                                    "Указанная категория не существует"
                            )
                    ));

                bike.setCategory(category);
            }
        }

        return product;
    }
}
