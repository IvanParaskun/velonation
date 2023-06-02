package org.velonation.core.datasource.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Product implements Comparable<Product> {
    @Id
    @GenericGenerator(name="Generator", strategy = "org.velonation.core.datasource.Generator")
    @GeneratedValue(generator = "Generator")
    private Integer id;

    @NotNull(message = "Это обязательное поле")
    @Length(min = 3, max = 50, message = "Это поле должно содержать от 3 до 50 символов")
    @Column(unique = true)
    private String name;

    @NotNull(message = "Это обязательное поле")
    @Min(value = 1, message = "Минимальное значение: 1")
    @Max(value = 500000, message = "Максимальное значение: 500000")
    private Integer price;

    @Nullable
    @Min(value = 1, message = "Минимальное значение: 1")
    @Max(value = 99, message = "Максимальное значение: 99")
    private Integer discount;

    private String imageUrl;
    private boolean available = false;

    @Transient
    private MultipartFile file;

    @Nullable
    public Integer getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("#%d %s", id, name);
    }

    @Override
    public int compareTo(Product product) {
        return name.compareTo(product.getName());
    }
}
