package org.velonation.core.datasource.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;


@Entity
@Table(name = "scooters")
@Getter @Setter
public class Scooter extends Product {
    @Nullable @Length(min = 3, max = 100, message = "Это поле может содержать от 3 до 100 симоволов")
    @Column(columnDefinition = "TEXT")
    protected String heading;

    @Nullable @Length(min = 3, max = 500, message = "Это поле может содержать от 3 до 500 симоволов")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(value = 2000, message = "Минимальное значение: 2000") @Max(value = 2023, message = "Максимальное значение: 2023")
    private Integer modelYear;

    @Min(value = 1, message = "Минимальное значение: 1") @Max(value = 1000, message = "Максимальное значение: 1000")
    private Integer wheelSize;

    @Min(value = 1, message = "Минимальное значение: 1") @Max(value = 200, message = "Максимальное значение: 200")
    private Integer maxWeight;

    @Min(value = 1, message = "Минимальное значение: 1") @Max(value = 500, message = "Максимальное значение: 500")
    private Integer height;

    @Min(value = 1, message = "Минимальное значение: 1") @Max(value = 500, message = "Максимальное значение: 500")
    private Double steeringWidth;

    @Min(value = 1, message = "Минимальное значение: 1") @Max(value = 100, message = "Максимальное значение: 100")
    private Double weight;
}
