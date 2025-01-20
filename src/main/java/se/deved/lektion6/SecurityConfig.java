package se.deved.lektion6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Denna klass definierar de beans (dependencies) som Spring behöver för
// att kunna konfigurera säkerhetsinställningar (som endpoint authentication exempelvis).
@Configuration // Denna gör det möjligt att skapa beans genom @Bean metoder
@EnableWebSecurity // Denna säger åt Spring att vi vill konfigurerar säkerhet i denna klass
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserRepository userRepository) throws Exception {
        // Vi måste stänga av CSRF
        // Mer om detta i nästa kurs
        http.csrf(AbstractHttpConfigurer::disable)
                // Innehållet i denna metod konfigurerar
                // vilka endpoints som skall kräva autentisering
                .authorizeHttpRequests(auth -> {
                    auth
                            // requestMatches säger vilken endpoint som vi skall applicera säkerhet på
                            // permitAll innebär att man inte måste autentisera sig
                            .requestMatchers("/register").permitAll()
                            .requestMatchers("/login").permitAll()
                            // authenticated innebär att man måste autentisera sig
                            .requestMatchers("/test").authenticated();
                })
                // För att kunna autentisera användare måste vi kunna verifera JWT tokens.
                // Det kan göras genom ett filter som körs för varje request.
                // Här registrerar vi detta filter innan ett av Springs egna filter som vi inte vill använda,
                // och därför lägger vi vårat filter innan deras filter.
                .addFilterBefore(new AuthenticationFilter(userRepository), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // PasswordEncoder är ett inbyggt interface med funktioner för password hashing
    // Vi kan använda det genom att definiera en bean med exempelvis BCrypt.
    // Se UserController för användning i register och login.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
