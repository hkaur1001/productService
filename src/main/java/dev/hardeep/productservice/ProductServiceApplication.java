package dev.hardeep.productservice;

import dev.hardeep.productservice.controllers.ProductController;
import dev.hardeep.productservice.models.Product;
import dev.hardeep.productservice.services.FakeStoreProductService;
import dev.hardeep.productservice.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductServiceApplication.class, args);

  }

}

