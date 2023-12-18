package lt.codeacademy.baigiamasisdarbas.service;


import lt.codeacademy.baigiamasisdarbas.dto.AuthResponse;
import lt.codeacademy.baigiamasisdarbas.dto.LoginRequest;
import lt.codeacademy.baigiamasisdarbas.dto.UserCreateRequest;
import lt.codeacademy.baigiamasisdarbas.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(UserCreateRequest createRequest);

    UserDTO loadUserByUsername(String username);

}
