package Homework_4.repositories;

import Homework_4.entities.User;
import Homework_4.entities.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    List<UserDto> findAllBy();
    Optional<User> findOneByPhone(String phone);
    Optional<User> findOneByEmail(String email);
    List<User> findAllByEmailContainsAndUserBlockFalse(String email);
    boolean existsByPhone(String phone);
}
