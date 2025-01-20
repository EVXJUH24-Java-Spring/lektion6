package se.deved.lektion6;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    // Metoden körs innan varje request
    @Override
    protected void doFilterInternal(
            // Innehåller information om requesten som headers och body
            HttpServletRequest request,
            // Innehåller information om responsen som skall skickas, men vi använder den inte
            HttpServletResponse response,
            // Innehåller en lista på alla andra filter som finns i Spring
            // Vi använder den endast för att gå vidare i "kedjan" när vårat filter är färdigt
            FilterChain filterChain
    ) throws ServletException, IOException {
        // Hämta ut token värdet från "Authorization" headern.
        // Om den är null ignorerar vi filtret och går till nästa filter.
        // Om en endpoint kräver autentisering men ingen token kommer in, kommer Spring i ett senare filter blocka detta.
        // Vi blockar inte åtkomst själva.
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // JWT kräver en algoritm för att kunna kryptera tokens
        Algorithm algorithm = Algorithm.HMAC256("secretnooneshallsee");
        // För att verifiera tokens måste man skala en "JWTVerifier" som innehåller en 'verify' metod.
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();

        // Verifiera token värdet från användaren och hämta ut användarens id
        DecodedJWT jwt = verifier.verify(authHeader);
        UUID userId = UUID.fromString(jwt.getSubject());

        // Se om det finns en användare i databasen med id:t
        User user = userRepository.findById(userId).orElseThrow();

        // Om det finns en användare så har den autentiserat sig och då måste vi informera Spring om det
        // Denna rad säger åt Spring att användaren är inloggad och kan fortsätta till endpointen.
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                user, user.getPassword(), new ArrayList<>()
        ));

        // När har autentiserat hos vill vi gå till nästa filter och till slut endpointen.
        // Denna rad anropar nästa filter i kedjan (antagligen inbyggda Spring filter).
        filterChain.doFilter(request, response);
    }
}
