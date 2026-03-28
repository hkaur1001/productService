package dev.hardeep.productservice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {

    private Long id;

    @NotBlank
    private String title;

    private String image;
    private String description;

    @NotBlank
    private String category;

    @Positive
    private double price;

    @Min(0)
    private Integer quantity;




    //In future there might be a case when to crete a product we might need to have separate parameters like below
    // private Long userId;
}
