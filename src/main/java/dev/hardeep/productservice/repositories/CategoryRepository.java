package dev.hardeep.productservice.repositories;

import dev.hardeep.productservice.models.Category;
import dev.hardeep.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

}
