package dev.hardeep.productservice.repositories;

import dev.hardeep.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long > {
    //Product is the entity we are working with.
    Product save(Product p);

    List<Product> findAll();

    Product findByIdIs(Long id);
}
