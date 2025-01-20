package se.deved.lektion6;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@SpringBootApplication
public class Lektion6Application {

    public static void main(String[] args) {
        /*Algorithm algorithm = Algorithm.HMAC256("secretnooneshallsee");

        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject("Ironman")
                .withClaim("loginDate", new Date())
                .withExpiresAt(Instant.now().plus(30, ChronoUnit.SECONDS))
                .sign(algorithm);

        System.out.println(token);

        JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();

		DecodedJWT jwt = verifier.verify(token);

		System.out.println(jwt.getSubject());*/

        SpringApplication.run(Lektion6Application.class, args);
    }

}

/*

Autentisering / Authentication
Auktorisering / Authorization

Användare: namn & lösenord

Permissions
Authorities
Access
Privileges

1. Registrera en användare
2. Logga in på användare
3. Anropa endpoint
4. Autentisera användare

Varje request: skicka in namn och lösenord

Logga in: skicka in namn och lösenord
Man får tillbaka en JWT

Varje request: skicka in JWT
JWT: JSON Web Token

 */