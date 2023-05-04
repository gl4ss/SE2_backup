package project.ecom.se2_backup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ecom.se2_backup.model.Cart;
import project.ecom.se2_backup.model.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserOrder(User user);
}
