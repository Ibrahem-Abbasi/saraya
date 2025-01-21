package saraya.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import saraya.services.user.UserService;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {
    private final UserService userService;
    private final JWTAuthenticationFilter jwtFilter;

    @Autowired
    public SecurityConfig(UserService userService, JWTAuthenticationFilter jwtFilter) {
        this.userService = userService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
                    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                    corsConfiguration.setMaxAge(Duration.ofMinutes(5L));
                    return corsConfiguration;
                })).csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/login").permitAll();

                    auth.requestMatchers("/signup").hasAnyAuthority("ADMIN", "SUPER_ADMIN");
                    auth.requestMatchers("/user/disable/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN");
                    auth.requestMatchers("/user/promote/**").hasAuthority("ADMIN");

                    auth.requestMatchers("/area/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN");
                    auth.requestMatchers("/question/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN");
                    auth.requestMatchers("/report/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN");

                    auth.requestMatchers(HttpMethod.DELETE, "/group/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/student/**").hasAnyAuthority("ADMIN", "SUPER_ADMIN");

                    auth.requestMatchers("/user/change-position/**").hasAuthority("SUPER_ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("SUPER_ADMIN");

                    auth.requestMatchers(HttpMethod.GET, "/user/**").authenticated();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
