package dev.hardeep.productservice.services;

import dev.hardeep.productservice.dtos.CreateProductRequestDto;
import dev.hardeep.productservice.exceptions.ProductNotFoundException;
import dev.hardeep.productservice.models.Category;
import dev.hardeep.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    Product[] getCategoryProduct(String categoryName);
    //List<Product> getProducts();
    Product createProduct(CreateProductRequestDto requestDto);

    void deleteProduct(Long productId);
    Product updateProduct(Long productId, String title, String description, String category, double price, String image);

    List<String> getAllCategories();
    Page<Product> getProducts(int page, int size, String sortBy);

}
