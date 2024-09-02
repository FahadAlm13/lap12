package spring.boot.lap12security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.lap12security.Model.User;

public interface AuthRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);

    User findUserByUsername(String username);
}
