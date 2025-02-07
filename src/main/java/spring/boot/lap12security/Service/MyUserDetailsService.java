package spring.boot.lap12security.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.boot.lap12security.API.ApiException;
import spring.boot.lap12security.Model.User;
import spring.boot.lap12security.Repository.AuthRepository;
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user;
    }
}
