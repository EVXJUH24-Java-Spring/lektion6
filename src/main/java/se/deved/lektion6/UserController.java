package se.deved.lektion6;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO dto) {
        User user = new User(dto.username, passwordEncoder.encode(dto.password));
        userRepository.save(user);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO dto) {
        User user = userRepository.findByName(dto.username).orElseThrow();

        if (!passwordEncoder.matches(dto.password, user.getPassword())) {
            return null;
        }

        Algorithm algorithm = Algorithm.HMAC256("secretnooneshallsee");

        return JWT.create()
                .withIssuer("auth0")
                .withSubject(user.getId().toString())
                .withClaim("loginDate", new Date())
                .withExpiresAt(Instant.now().plus(30, ChronoUnit.SECONDS))
                .sign(algorithm);
    }

    public static class UserDTO {
        public String username, password;
    }
}
