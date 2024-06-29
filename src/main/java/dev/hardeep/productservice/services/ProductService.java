package dev.hardeep.productservice.services;

import dev.hardeep.productservice.exceptions.ProductNotFoundException;
import dev.hardeep.productservice.models.Category;
import dev.hardeep.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    Product[] getCategoryProduct(String categoryName);
    List<Product> getProducts();
    Product createProduct(String title, String description, String category, double price, String image);

    void deleteProduct(Long productId);
    Product updateProduct(Long productId, String title, String description, String category, double price, String image);

    List<String> getAllCategories();
}
