package lt.codeacademy.baigiamasisdarbas.controller;


import jakarta.validation.Valid;
import lt.codeacademy.baigiamasisdarbas.config.JwtTokenManager;
import lt.codeacademy.baigiamasisdarbas.dto.AuthResponse;
import lt.codeacademy.baigiamasisdarbas.dto.LoginRequest;
import lt.codeacademy.baigiamasisdarbas.dto.UserCreateRequest;
import lt.codeacademy.baigiamasisdarbas.exception.AppException;
import lt.codeacademy.baigiamasisdarbas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.Objects;

@RestController
public class AuthController {

    private final JwtTokenManager jwtTokenManager;
    private final UserService userService;

    public AuthController(JwtTokenManager jwtTokenManager, UserService userService) {
        this.jwtTokenManager = jwtTokenManager;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AppException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        AuthResponse authResponse = userService.login(loginRequest);
        authResponse.setJwtToken(jwtTokenManager.generateToken(authResponse.getUsername()));
        return ResponseEntity.ok(authResponse);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserCreateRequest rq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AppException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        AuthResponse authResponse = userService.register(rq);
        authResponse.setJwtToken(jwtTokenManager.generateToken(authResponse.getUsername()));
        return ResponseEntity.created(URI.create("/users/" + authResponse.getId())).body(authResponse);
    }

}
