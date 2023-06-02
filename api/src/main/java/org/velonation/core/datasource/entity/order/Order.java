package org.velonation.core.datasource.entity.order;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.velonation.core.datasource.entity.product.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Entity
@Table(name = "orders")
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Order {
    @Id
    @GenericGenerator(name = "Generator", strategy = "org.velonation.core.datasource.Generator")
    @GeneratedValue(generator = "Generator")
    private Integer id;

    @NotNull(message = "Это обязательное поле")
    @Length(max=100, message = "Это поле может содержать до 100 символов")
    @Email(message = "Неверный формат электронной почты")
    private String email;

    @NotNull(message = "Это обязательное поле") @Length(min = 16, max=16, message = "Неверный формат номера телефона")
    private String phoneNumber;

    @Embedded @Valid
    private DeliveryInfo deliveryInfo;

    @NotNull(message = "Пустая корзина") @NotEmpty(message = "Пустая корзина")
    @ElementCollection(fetch = FetchType.EAGER) @CollectionTable(name = "carts", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id") @Column(name = "count")
    private Map<Product, Integer> cart = new HashMap<>();

    @Embeddable @Getter @Setter
    public static class DeliveryInfo {
        @NotNull(message = "Это обязательное поле") @Length(min=2, max=50, message = "Поле может содержать от 3 до 50 симоволов")
        private String name;

        @NotNull(message = "Это обязательное поле") @Length(min=3, max=50, message = "Поле может содержать от 3 до 50 симоволов")
        private String street;

        @NotNull(message = "Это обязательное поле") @Length(min=1, max=50, message = "Поле может содержать от 3 до 50 симоволов")
        private String houseNumber;

        @Length(min=1, max = 10, message = "Поле может содержать от 1 до 10 симоволов")
        private String apartments;
    }

    public Integer total() {
        return cart.entrySet().stream().mapToInt(
                entry -> entry.getValue() *
                        (entry.getKey().getPrice() -
                                (entry.getKey().getPrice() / 100 *
                                        (entry.getKey().getDiscount() == null ? 0 : entry.getKey().getDiscount())))
        ).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && email.equals(order.email) && phoneNumber.equals(order.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, phoneNumber);
    }
}
