package project.ecom.se2_backup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import project.ecom.se2_backup.dto.ProductDto;
import project.ecom.se2_backup.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p " +
            "where p.category.name = :category_name " +
            "order by p.category.name asc")
    List<Product> sortProductsByCategory(@Param("category_name") String category);
}
