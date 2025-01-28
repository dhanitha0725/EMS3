package com.example.EmployeeManagementSystem.Config;

import com.example.EmployeeManagementSystem.shared.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    // AuthenticationProvider handles the actual authentication logic
    private final AuthenticationProvider authenticationProvider;

    // JwtAuthenticationFilter validates JWT tokens in incoming requests
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    //    Constructor to initialize SecurityConfiguration with required dependencies.
    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    //    Configures the security filter chain for the application.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection as the application is stateless and uses JWT
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())

                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/employee/save").permitAll()
                        .anyRequest().authenticated()      // Secure all other endpoints
                )

                // Configure session management as stateless since JWT handles authentication
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Set the custom authentication provider
                .authenticationProvider(authenticationProvider)

                // Add the JWT authentication filter before processing username/password authentication
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Build and return the security filter chain
        return http.build();
    }

    //    Configures Cross-Origin Resource Sharing (CORS) settings for the application.
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Define allowed origins for cross-origin requests
        configuration.setAllowedOrigins(List.of("http://localhost:8090"));

        // Define allowed HTTP methods
        configuration.setAllowedMethods(List.of("GET", "POST"));

        // Define allowed headers in requests
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // Allow cookies or other credentials in cross-origin requests
        configuration.setAllowCredentials(true);

        // Expose custom headers in the response
        configuration.addExposedHeader("Authorization");

        // Register the CORS configuration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


}
