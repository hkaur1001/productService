package dev.hardeep.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {

    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;




    //In future there might be a case when to crete a product we might need to have separate parameters like below
    // private Long userId;
}
