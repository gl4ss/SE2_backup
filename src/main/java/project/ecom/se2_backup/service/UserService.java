package project.ecom.se2_backup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.ecom.se2_backup.model.Product;
import project.ecom.se2_backup.model.User;
import project.ecom.se2_backup.repository.UserRepository;
import project.ecom.se2_backup.security.CustomUser;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User id " + id + " not found"));
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        User preUser = userRepository.getUserByEmail(user.getEmail());

        if (preUser != null) {
            preUser.setFirstName(user.getFirstName());
            preUser.setLastName(user.getLastName());
            preUser.setEmail(user.getEmail());
            saveUser(preUser);
        }
    }

    public boolean existByEmail(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);

        return CustomUser.build(user);
    }
}
