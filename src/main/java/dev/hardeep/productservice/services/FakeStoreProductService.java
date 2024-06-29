package dev.hardeep.productservice.services;

import dev.hardeep.productservice.dtos.FakeStoreProductDto;
import dev.hardeep.productservice.exceptions.ProductNotFoundException;
import dev.hardeep.productservice.models.Category;
import dev.hardeep.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/"+productId,
                FakeStoreProductDto.class);

        if(fakeStoreProductResponse.getStatusCode() != HttpStatusCode.valueOf(200)){

        }
        //fakeStoreProductResponse.getHeaders();
        FakeStoreProductDto fakeStoreProduct = fakeStoreProductResponse.getBody();
        if(fakeStoreProduct == null){
            throw new ProductNotFoundException("Product with ID: "+productId+" does not exist. Retry with some other Product ID");
        }
        return fakeStoreProduct.toProduct();
    }

    @Override
    public Product[] getCategoryProduct(String categoryName) {


        FakeStoreProductDto[] fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+categoryName,
                FakeStoreProductDto[].class);
        Product[] product = new Product[fakeStoreProduct.length];
        for(int i=0; i<fakeStoreProduct.length; i++){
            product[i] = fakeStoreProduct[i].toProduct();
        }
        return product;
    }



    @Override
    public List<Product> getProducts() {
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
                );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct : fakeStoreProducts){
            products.add(fakeStoreProduct.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct( String title, String description, String category, double price, String image) {

        FakeStoreProductDto fakeStoreProductDto =  new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);

        //url , requestBody
       FakeStoreProductDto response =  restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
       return response.toProduct();
    }

    @Override
    public void deleteProduct(Long productId) {
     restTemplate.delete(
                "https://fakestoreapi.com/products/"+productId);

    }

    @Override
    public Product updateProduct(Long productId, String title, String description, String category, double price, String image) {
        FakeStoreProductDto fakeStoreProductDto =  new FakeStoreProductDto();

        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);

        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/"+productId,
                HttpMethod.PUT, requestEntity, FakeStoreProductDto.class );

        return response.getBody().toProduct();
    }

    @Override
    public List<String> getAllCategories() {

         List<String> allCategories = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                List.class);
        return allCategories;

    }


}
