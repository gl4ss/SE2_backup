package project.ecom.se2_backup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ecom.se2_backup.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String userName);

    Boolean existsByEmail(String username);
}
