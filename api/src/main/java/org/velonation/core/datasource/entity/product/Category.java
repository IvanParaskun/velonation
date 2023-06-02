package org.velonation.core.datasource.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor
public class Category implements Comparable<Category> {
    @Id
    @GenericGenerator(name="Generator", strategy = "org.velonation.core.datasource.Generator")
    @GeneratedValue(generator = "Generator")
    protected Integer id;

    @NotNull(message = "Это обязательное поле") @Length(min=3, max=20, message = "Это поле может содержать от 3 до 20 симоволов")
    @Column(unique = true)
    private String name;

    @Min(value = 0, message = "Минимальное значение: 0") @Max(value = 99, message = "Максимальное значение: 99")
    private Integer discount;

    @OneToMany(mappedBy = "category")
    private List<Bike> bikes = new ArrayList<>();

    @Override
    public int compareTo(Category category) {
        return name.compareTo(category.getName());
    }

    public boolean isAvailable() {
        return bikes.stream().filter(Bike::isAvailable).toList().size() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(id, category.id) && name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
