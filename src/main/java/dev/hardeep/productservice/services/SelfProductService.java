package dev.hardeep.productservice.services;

import dev.hardeep.productservice.exceptions.ProductNotFoundException;
import dev.hardeep.productservice.models.Category;
import dev.hardeep.productservice.models.Product;
import dev.hardeep.productservice.repositories.CategoryRepository;
import dev.hardeep.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;



    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        return productRepository.findByIdIs(productId);
    }

    @Override
    public Product[] getCategoryProduct(String categoryName) {
        return new Product[0];
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);


        Category categoryFromDataBase = categoryRepository.findByTitle(category);
        if(categoryFromDataBase == null){
            Category newCategory = new Category();
            newCategory.setTitle(title);
            categoryFromDataBase = newCategory;
        }
        product.setCategory(categoryFromDataBase);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public Product updateProduct(Long productId, String title, String description, String category, double price, String image) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }


}
