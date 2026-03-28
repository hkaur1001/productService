package dev.hardeep.productservice.services;

import dev.hardeep.productservice.dtos.CreateProductRequestDto;
import dev.hardeep.productservice.exceptions.ProductNotFoundException;
import dev.hardeep.productservice.models.Category;
import dev.hardeep.productservice.models.Product;
import dev.hardeep.productservice.repositories.CategoryRepository;
import dev.hardeep.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    //constructor injection 
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    public Page<Product> getProducts(int page, int size, String sortBy) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Override
    public Product createProduct(CreateProductRequestDto requestDto) {

        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setImageUrl(requestDto.getImage());
        product.setQuantity(requestDto.getQuantity() != null ? requestDto.getQuantity() : 10);


        Category categoryFromDataBase = categoryRepository.findByTitle(requestDto.getCategory());
        if(categoryFromDataBase == null){
            Category newCategory = new Category();
            newCategory.setTitle(requestDto.getCategory());
            categoryFromDataBase = newCategory;
        }
        product.setCategory(categoryFromDataBase);
        return productRepository.save(product);
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
