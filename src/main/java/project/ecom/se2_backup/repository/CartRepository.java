package project.ecom.se2_backup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ecom.se2_backup.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
