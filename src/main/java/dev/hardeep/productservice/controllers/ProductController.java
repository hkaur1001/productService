package dev.hardeep.productservice.controllers;

import dev.hardeep.productservice.dtos.CreateProductRequestDto;
import dev.hardeep.productservice.dtos.ErrorDto;
import dev.hardeep.productservice.exceptions.ProductNotFoundException;
import dev.hardeep.productservice.models.Category;
import dev.hardeep.productservice.models.Product;
import dev.hardeep.productservice.services.FakeStoreProductService;
import dev.hardeep.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    //POST  /products
    //Create product
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto){

         return productService.createProduct(requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getImage());
        //there is no Confidential Parameter in product , so we can return product,
        // return Dto if there is some Confidential Parameter
    }

    // GET product with specific id
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
    }

    //Get products in a specific category
    @GetMapping("/products/category/{categoryName}")
    public Product[] GetProductsForSpecificCategory(@PathVariable("categoryName") String category){
       Product[] product = productService.getCategoryProduct(category);
       return product;
    }

    //Delete a product
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
      productService.deleteProduct(productId);
    }

    //get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
             List<Product> products = productService.getProducts();
             ResponseEntity<List<Product>> response= new ResponseEntity<>(products, HttpStatus.OK);
             return response;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody CreateProductRequestDto requestDto){
        return productService.updateProduct(productId, requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getImage());

    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
           List<String> response = productService.getAllCategories();
           return response;
    }

    /*@ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception){

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        //This exception handler is limited to only the exceptions thrown from this controller
        //execute this method whenever a productNotFoundException is thrown

    }*/
}
