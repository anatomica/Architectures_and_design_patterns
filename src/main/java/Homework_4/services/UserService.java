package Homework_4.services;

import Homework_4.entities.User;
import Homework_4.entities.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User findById(Long id) throws Exception;
    Optional<User> findByPhone(String phone);
    List<User> findAllUsers();
    List<UserDto> getDtoUserData();
    List<User> findAllByEmail();
    void blockById(Long id);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
