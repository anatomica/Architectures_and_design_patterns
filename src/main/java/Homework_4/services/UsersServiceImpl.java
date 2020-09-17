package Homework_4.services;

import Homework_4.entities.Role;
import Homework_4.entities.User;
import Homework_4.entities.dto.UserDto;
import Homework_4.repositories.RolesRepository;
import Homework_4.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UserDetailsService, UserService {
    public UsersRepository usersRepository;
    public RolesRepository rolesRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public User findById(Long id) throws Exception {
        return usersRepository.findById(id).orElseThrow(() -> new Exception("Can't found user with id = " + id));
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return usersRepository.findOneByPhone(phone);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) usersRepository.findAll();
    }

    @Override
    public List<UserDto> getDtoUserData() {
        return usersRepository.findAllBy();
    }

    @Override
    public List<User> findAllByEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail;
        if (principal instanceof UserDetails) {
             userEmail = ((UserDetails)principal).getUsername();
            Collection<? extends GrantedAuthority> authorities = ((UserDetails)principal).getAuthorities();
        } else {
            userEmail = principal.toString();
        }
        return usersRepository.findAllByEmailContainsAndUserBlockFalse(userEmail);
    }

    @Override
    public void blockById(Long id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()){
            User u = user.get();
            u.setUserBlock(true);
            usersRepository.save(u);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findOneByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        if (!user.isUserBlock())
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
        else
            return new org.springframework.security.core.userdetails.User("block", "block",
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}