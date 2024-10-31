package com.ttnlr.planet.service;

import com.ttnlr.planet.exception.UsernameAlreadyExistsException;
import com.ttnlr.planet.model.User;
import com.ttnlr.planet.model.UserAuthority;
import com.ttnlr.planet.model.UserRole;
import com.ttnlr.planet.repository.UserRepository;
import com.ttnlr.planet.repository.UserRolesRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registration(String username, String password) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = userRepository.save(
                    new User()
                            .setId(null)
                            .setUsername(username)
                            .setPassword(passwordEncoder.encode(password))
                            .setLocked(false)
                            .setExpired(false)
                            .setEnabled(true)
            );
            userRolesRepository.save(new UserRole(null, UserAuthority.CUSTOMER, user));
        } else {
            throw new UsernameAlreadyExistsException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}