package lt.codeacademy.baigiamasisdarbas.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lt.codeacademy.baigiamasisdarbas.dto.UserDTO;
import lt.codeacademy.baigiamasisdarbas.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;


@Component
public class JwtTokenManager {
    private static final long TOKEN_EXPIRATION_TIME_MS = 3_600_000;
    private final UserService userService;

    @Autowired
    public JwtTokenManager(UserService userService) {
        this.userService = userService;
    }

    @Value("${security.jwt.token.secretKey}")
    private String secretKey;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenManager.class);

    @PostConstruct
    protected void init() {

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + TOKEN_EXPIRATION_TIME_MS);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decoded = verifier.verify(token);
            UserDTO user = userService.loadUserByUsername(decoded.getSubject());
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (JWTVerificationException e) {
            logger.error("Token validation failed: " + e.getMessage());
            return null;
        }


    }
}
