package lt.codeacademy.baigiamasisdarbas.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.baigiamasisdarbas.Entity.Role;
import lt.codeacademy.baigiamasisdarbas.Entity.User;
import lt.codeacademy.baigiamasisdarbas.dto.*;
import lt.codeacademy.baigiamasisdarbas.exception.AppException;
import lt.codeacademy.baigiamasisdarbas.repository.RoleRepository;
import lt.codeacademy.baigiamasisdarbas.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        Set<Role> roles = new HashSet<>();
        try {
            if (user == null) {
                throw new AppException("Invalid username", HttpStatus.NOT_FOUND);
            }
            if (!passwordEncoder.matches(CharBuffer.wrap(loginRequest.getPassword()), user.getPassword())) {
                throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
            }
            roles = user.getRoles().stream().map(role -> new Role(role.getRoleId(), role.getRoleType())).collect(Collectors.toSet());
        } catch (AppException e) {
            throw new AppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new AppException("Login failed", HttpStatus.BAD_REQUEST);
        }
        return new AuthResponse(user.getUserId(), user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getEmail(), user.getBirthDate(), "", roles);
    }

    @Override
    @Transactional
    public AuthResponse register(UserCreateRequest rq) {
        User savedUser = new User();
        Set<Role> roles = new HashSet<>();
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(rq.getUsername()));
        if (optionalUser.isPresent()) {
            throw new AppException("Username already taken", HttpStatus.BAD_REQUEST);
        }
        Optional<Role> role = Optional.ofNullable(roleRepository.findByRoleType("USER"));

        if (role.isEmpty()) {
            throw new AppException("Role not found", HttpStatus.NOT_FOUND);
        }
        try {
            User user = new User();
            user.setUsername(rq.getUsername());
            user.setPassword(passwordEncoder.encode(rq.getPassword()));
            user.setFirst_name(rq.getName());
            user.setLast_name(rq.getLastName());
            user.setEmail(rq.getEmail());
            user.setBirthDate(rq.getDateOfBirth());
            roles.add(roleRepository.findByRoleType("USER"));
            user.setRoles(roles);
            userRepository.save(user);
        } catch (Exception e) {
            throw new AppException("Registration failed", HttpStatus.BAD_REQUEST);
        }
        return new AuthResponse(savedUser.getUserId(), savedUser.getFirst_name(), savedUser.getLast_name(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getBirthDate(), "", roles);
    }

    @Override
    public UserDTO loadUserByUsername(String username) throws AppException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AppException("Invalid username or password", HttpStatus.NOT_FOUND);
        }
        return new UserDTO(user.getUserId(), user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getEmail(), user.getBirthDate());

    }
}
