package pl.game.tictac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and() // Use configured CORS
                .authorizeHttpRequests(authz -> authz

//                         // Allow public access to certain get requests
//                         .requestMatchers(HttpMethod.GET, "/api/game/**").permitAll()
//                         .requestMatchers(HttpMethod.POST, "/api/game/join/{gameId}").permitAll()
//                         .requestMatchers(HttpMethod.GET, "/api/game/{gameId}").permitAll()
//                         .requestMatchers(HttpMethod.POST, "/api/game/{gameId}/move").permitAll()

// //                        .requestMatchers(HttpMethod.POST, "/api/join/**").permitAll()

//                         // Below are hypothetical endpoints you might have. Adjust them to fit your actual API.
//                                 .requestMatchers(HttpMethod.POST, "/api/user/signup").permitAll()
//                                 .requestMatchers(HttpMethod.GET, "/api/user/leaderboard").permitAll()
//                         .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()
//                         .requestMatchers(HttpMethod.POST, "/api/game/create").permitAll()
//                         .requestMatchers("/h2-console/**").permitAll() // Allow H2 console access
//                         // All other requests should be authenticated
//                         .anyRequest().authenticated()
                        .anyRequest().permitAll()
                        
                )
                
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())                
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
                // .httpBasic(withDefaults())
                // .sessionManagement(session -> session
                //         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // )
                // .build();
    }

}
