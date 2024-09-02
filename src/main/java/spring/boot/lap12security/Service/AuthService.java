package spring.boot.lap12security.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.lap12security.API.ApiException;
import spring.boot.lap12security.Model.User;
import spring.boot.lap12security.Repository.AuthRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register(User user) {
        user.setRole("USER");

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);

        authRepository.save(user);
    }
    public List<User> getAllUsers() {
        return authRepository.findAll();
    }
    public void updateUser(User user,Integer id) {
        User user1 = authRepository.findUserById(id);
        if(user1 == null) {
            throw new ApiException("User not found");
        }
        user1.setUsername(user.getUsername());

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setPassword(hash);

        authRepository.save(user1);
    }
    public void deleteUser(Integer id) {
        User user1 = authRepository.findUserById(id);
        if(user1 == null) {
            throw new ApiException("User not found");
        }
        authRepository.delete(user1);
    }
}
