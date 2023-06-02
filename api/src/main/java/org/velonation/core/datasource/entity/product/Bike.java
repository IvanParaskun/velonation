package org.velonation.core.datasource.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;


@Entity
@Table(name = "bikes")
@Getter @Setter @NoArgsConstructor
public class Bike extends Product {
    @NotNull(message = "Это обязательное поле") @Length(min = 3, max = 15, message = "Это поле может содержать от 3 до 15 симоволов")
    private String brand;

    @NotNull(message = "Это обязательное поле") @Length(min = 3, max = 35, message = "Это поле может содержать от 3 до 30 симоволов")
    private String model;

    @Min(value = 2000, message = "Минимальное значение: 2000") @Max(value = 2023, message = "Максимальное значение: 2023")
    private Integer modelYear;

    @NotNull(message = "Это обязательное поле") @Length(min=7, max = 7, message = "Это поле может содержать ровно 7 симоволов")
    private String gender;

    @JsonIgnoreProperties({"bikes"})
    @Nullable @ManyToOne @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    @Length(min = 3, max=100, message = "Это поле может содержать от 3 до 100 симоволов")
    @Column(columnDefinition = "TEXT")
    private String heading = "Бесспорный хозяин на асфальте и бездорожье";

    @Length(min = 3, max=500, message = "Это поле может содержать от 3 до 500 симоволов")
    @Column(columnDefinition = "TEXT")
    private String description;

    boolean bannered = false;
    boolean carded = false;

    @Embedded @Valid
    private Frame frame;

    @Embedded @Valid
    private Transmission transmission;

    @Embedded @Valid
    private Wheel wheel;

    @Embedded @Valid
    private BrakeSystem brakeSystem;

    @Transient
    private Integer categoryId;

    @Override @Nullable
    public Integer getDiscount() {
        if (category != null && category.getDiscount() != null)
            return category.getDiscount();

        return super.getDiscount();
    }

    @Embeddable @Getter @Setter
    public static class Frame {
        @Min(value = 1, message = "Минимальное значение: 1") @Max(value = 100, message = "Максимальное значение: 100")
        private Integer frameSize;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String material;

        private Boolean isFolding;
    }

    @Embeddable @Getter @Setter
    public static class Transmission {
        @Min(value = 0, message = "Минимальное значение: 0") @Max(value = 50, message = "Максимальное значение: 50")
        private Integer numberOfSpeeds;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String switchesType;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String switches;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String chain;
    }

    @Embeddable @Getter @Setter
    public static class Wheel {
        @Min(value = 1, message = "Минимальное значение: 1") @Max(value = 100, message = "Максимальное значение: 100")
        private Double wheelSize;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String bushings;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String rimMaterial;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String tires;
    }

    @Embeddable @Getter @Setter
    public static class BrakeSystem {
        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        private String brakesType;

        @Length(min = 3, max = 50, message = "Это поле может содержать от 3 до 50 симоволов")
        public String brakes;
    }

    public void setBrand(String brand) {
        this.brand = brand;
        setName(this.brand + (this.model == null ? "" : " " + this.model));
    }

    public void setModel(String model) {
        this.model = model;
        setName((this.brand == null ? "" : this.brand + " ") + this.model);
    }
}